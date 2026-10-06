package ru.mail.auth.request;

import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import ru.ok.android.api.core.ApiInvocationException;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
@Deprecated(message = "You can remove MigrateToPostConfig usage")
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\b\u0007\u0018\u00002\u00020\u0001Ba\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0003¢\u0006\u0004\b\f\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u000eR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u000eR\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u000eR\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000eR\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u000e¨\u0006\u000f"}, d2 = {"Lru/mail/auth/request/MigrateToPostConfig;", "", "is12127Enabled", "", "is12131Enabled", "is12132Enabled", "is12142Enabled", "is12143Enabled", "is12144Enabled", "is12146Enabled", "is12147Enabled", "is12181Enabled", "<init>", "(ZZZZZZZZZ)V", "()Z", "mail-id-sdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class MigrateToPostConfig {
    private final boolean is12127Enabled;
    private final boolean is12131Enabled;
    private final boolean is12132Enabled;
    private final boolean is12142Enabled;
    private final boolean is12143Enabled;
    private final boolean is12144Enabled;
    private final boolean is12146Enabled;
    private final boolean is12147Enabled;
    private final boolean is12181Enabled;

    public MigrateToPostConfig() {
        this(false, false, false, false, false, false, false, false, false, ApiInvocationException.ErrorCodes.IDS_BLOCKED, null);
    }

    /* JADX INFO: renamed from: is12127Enabled, reason: from getter */
    public final boolean getIs12127Enabled() {
        return this.is12127Enabled;
    }

    /* JADX INFO: renamed from: is12131Enabled, reason: from getter */
    public final boolean getIs12131Enabled() {
        return this.is12131Enabled;
    }

    /* JADX INFO: renamed from: is12132Enabled, reason: from getter */
    public final boolean getIs12132Enabled() {
        return this.is12132Enabled;
    }

    /* JADX INFO: renamed from: is12142Enabled, reason: from getter */
    public final boolean getIs12142Enabled() {
        return this.is12142Enabled;
    }

    /* JADX INFO: renamed from: is12143Enabled, reason: from getter */
    public final boolean getIs12143Enabled() {
        return this.is12143Enabled;
    }

    /* JADX INFO: renamed from: is12144Enabled, reason: from getter */
    public final boolean getIs12144Enabled() {
        return this.is12144Enabled;
    }

    /* JADX INFO: renamed from: is12146Enabled, reason: from getter */
    public final boolean getIs12146Enabled() {
        return this.is12146Enabled;
    }

    /* JADX INFO: renamed from: is12147Enabled, reason: from getter */
    public final boolean getIs12147Enabled() {
        return this.is12147Enabled;
    }

    /* JADX INFO: renamed from: is12181Enabled, reason: from getter */
    public final boolean getIs12181Enabled() {
        return this.is12181Enabled;
    }

    public MigrateToPostConfig(boolean z10, boolean z11, boolean z12, boolean z13, boolean z14, boolean z15, boolean z16, boolean z17, boolean z18) {
        this.is12127Enabled = z10;
        this.is12131Enabled = z11;
        this.is12132Enabled = z12;
        this.is12142Enabled = z13;
        this.is12143Enabled = z14;
        this.is12144Enabled = z15;
        this.is12146Enabled = z16;
        this.is12147Enabled = z17;
        this.is12181Enabled = z18;
    }

    public /* synthetic */ MigrateToPostConfig(boolean z10, boolean z11, boolean z12, boolean z13, boolean z14, boolean z15, boolean z16, boolean z17, boolean z18, int i10, DefaultConstructorMarker defaultConstructorMarker) {
        this((i10 & 1) != 0 ? true : z10, (i10 & 2) != 0 ? true : z11, (i10 & 4) != 0 ? true : z12, (i10 & 8) != 0 ? true : z13, (i10 & 16) != 0 ? true : z14, (i10 & 32) != 0 ? true : z15, (i10 & 64) != 0 ? true : z16, (i10 & 128) != 0 ? true : z17, (i10 & 256) != 0 ? true : z18);
    }
}
