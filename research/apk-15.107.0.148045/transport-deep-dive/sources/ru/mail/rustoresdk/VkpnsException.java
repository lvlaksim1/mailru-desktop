package ru.mail.rustoresdk;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00060\u0001j\u0002`\u0002:\u0003\b\t\nB\u0011\b\u0004\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0007\u0082\u0001\u0003\u000b\f\r¨\u0006\u000e"}, d2 = {"Lru/mail/rustoresdk/VkpnsException;", "Ljava/lang/RuntimeException;", "Lkotlin/RuntimeException;", "isCritical", "", "<init>", "(Z)V", "()Z", "UnauthorizedException", "HostAppNotInstalledException", "HostAppBackgroundWorkPermissionNotGranted", "Lru/mail/rustoresdk/VkpnsException$HostAppBackgroundWorkPermissionNotGranted;", "Lru/mail/rustoresdk/VkpnsException$HostAppNotInstalledException;", "Lru/mail/rustoresdk/VkpnsException$UnauthorizedException;", "rustore-sdk-api_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class VkpnsException extends RuntimeException {
    private final boolean isCritical;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lru/mail/rustoresdk/VkpnsException$HostAppBackgroundWorkPermissionNotGranted;", "Lru/mail/rustoresdk/VkpnsException;", "<init>", "()V", "rustore-sdk-api_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class HostAppBackgroundWorkPermissionNotGranted extends VkpnsException {
        public HostAppBackgroundWorkPermissionNotGranted() {
            super(false, null);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lru/mail/rustoresdk/VkpnsException$HostAppNotInstalledException;", "Lru/mail/rustoresdk/VkpnsException;", "<init>", "()V", "rustore-sdk-api_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class HostAppNotInstalledException extends VkpnsException {
        public HostAppNotInstalledException() {
            super(true, null);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lru/mail/rustoresdk/VkpnsException$UnauthorizedException;", "Lru/mail/rustoresdk/VkpnsException;", "<init>", "()V", "rustore-sdk-api_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class UnauthorizedException extends VkpnsException {
        public UnauthorizedException() {
            super(true, null);
        }
    }

    public /* synthetic */ VkpnsException(boolean z10, DefaultConstructorMarker defaultConstructorMarker) {
        this(z10);
    }

    /* JADX INFO: renamed from: isCritical, reason: from getter */
    public final boolean getIsCritical() {
        return this.isCritical;
    }

    private VkpnsException(boolean z10) {
        this.isCritical = z10;
    }
}
