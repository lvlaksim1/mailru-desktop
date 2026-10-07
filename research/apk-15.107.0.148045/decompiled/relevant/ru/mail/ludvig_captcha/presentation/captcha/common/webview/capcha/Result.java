package ru.mail.ludvig_captcha.presentation.captcha.common.webview.capcha;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.annotations.NotNull;
import ru.mail.offline.attaches.storage.api.MailOfflineAttachmentPersistedCacheStatus;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lru/mail/ludvig_captcha/presentation/captcha/common/webview/capcha/Result;", "", "<init>", "(Ljava/lang/String;I)V", "SUCCESS", "CANCEL", MailOfflineAttachmentPersistedCacheStatus.ERROR, "ludvig-captcha_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public enum Result {
    SUCCESS,
    CANCEL,
    ERROR;

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    @NotNull
    public static EnumEntries<Result> getEntries() {
        return $ENTRIES;
    }
}
