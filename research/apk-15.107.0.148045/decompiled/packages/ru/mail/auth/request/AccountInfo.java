package ru.mail.auth.request;

import android.content.Context;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.auth.ActiveProfileManager;
import ru.mail.auth.util.CurrentAccountUtils;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\b\u0007\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007B\u001b\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\u0006\u0010\nB\u001b\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b\u0006\u0010\rJ\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\u001f\u0010\u0013\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00052\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0010¨\u0006\u0019"}, d2 = {"Lru/mail/auth/request/AccountInfo;", "", "login", "", "isAccountActive", "", "<init>", "(Ljava/lang/String;Z)V", "activeProfileManager", "Lru/mail/auth/ActiveProfileManager;", "(Ljava/lang/String;Lru/mail/auth/ActiveProfileManager;)V", "context", "Landroid/content/Context;", "(Ljava/lang/String;Landroid/content/Context;)V", "getLogin", "()Ljava/lang/String;", "()Z", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class AccountInfo {
    private final boolean isAccountActive;

    @Nullable
    private final String login;

    @JvmOverloads
    public AccountInfo(@Nullable String str) {
        this(str, false, 2, null);
    }

    public static /* synthetic */ AccountInfo copy$default(AccountInfo accountInfo, String str, boolean z10, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = accountInfo.login;
        }
        if ((i10 & 2) != 0) {
            z10 = accountInfo.isAccountActive;
        }
        return accountInfo.copy(str, z10);
    }

    @Nullable
    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getLogin() {
        return this.login;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getIsAccountActive() {
        return this.isAccountActive;
    }

    @NotNull
    public final AccountInfo copy(@Nullable String login, boolean isAccountActive) {
        return new AccountInfo(login, isAccountActive);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AccountInfo)) {
            return false;
        }
        AccountInfo accountInfo = (AccountInfo) other;
        return Intrinsics.areEqual(this.login, accountInfo.login) && this.isAccountActive == accountInfo.isAccountActive;
    }

    @Nullable
    public final String getLogin() {
        return this.login;
    }

    public int hashCode() {
        String str = this.login;
        return ((str == null ? 0 : str.hashCode()) * 31) + Boolean.hashCode(this.isAccountActive);
    }

    public final boolean isAccountActive() {
        return this.isAccountActive;
    }

    @NotNull
    public String toString() {
        return "AccountInfo(login=" + this.login + ", isAccountActive=" + this.isAccountActive + ")";
    }

    @JvmOverloads
    public AccountInfo(@Nullable String str, boolean z10) {
        this.login = str;
        this.isAccountActive = z10;
    }

    public /* synthetic */ AccountInfo(String str, boolean z10, int i10, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i10 & 2) != 0 ? false : z10);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public AccountInfo(@Nullable String str, @NotNull ActiveProfileManager activeProfileManager) {
        this(str, activeProfileManager.isLastActiveProfileLogin(str));
        Intrinsics.checkNotNullParameter(activeProfileManager, "activeProfileManager");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public AccountInfo(@Nullable String str, @NotNull Context context) {
        this(str, CurrentAccountUtils.isLastActiveProfileLogin(context, str));
        Intrinsics.checkNotNullParameter(context, "context");
    }
}
