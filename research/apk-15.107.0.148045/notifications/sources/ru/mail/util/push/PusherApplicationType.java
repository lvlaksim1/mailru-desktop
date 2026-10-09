package ru.mail.util.push;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.portal.apps.shared.presentationlayer.handlers.FileTypes;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\r\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0081\u0002\u0018\u0000 \u00102\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0010B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u000e\u001a\u00020\u000fj\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\r¨\u0006\u0011"}, d2 = {"Lru/mail/util/push/PusherApplicationType;", "", "<init>", "(Ljava/lang/String;I)V", "MAIL", FileTypes.CALENDAR, "TODO", "ADDRESS_BOOK", "PULSE", "CLOUD", "MORE", "BONUS", "MAIN_PAGE", "WALLET", "convertToString", "", "Companion", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public enum PusherApplicationType {
    MAIL,
    CALENDAR,
    TODO,
    ADDRESS_BOOK,
    PULSE,
    CLOUD,
    MORE,
    BONUS,
    MAIN_PAGE,
    WALLET;

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¨\u0006\b"}, d2 = {"Lru/mail/util/push/PusherApplicationType$Companion;", "", "<init>", "()V", "convertFromString", "Lru/mail/util/push/PusherApplicationType;", "name", "", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Nullable
        public final PusherApplicationType convertFromString(@Nullable String name) {
            if (name != null && !StringsKt.isBlank(name)) {
                try {
                    return PusherApplicationType.valueOf(name);
                } catch (IllegalArgumentException unused) {
                }
            }
            return null;
        }

        private Companion() {
        }
    }

    @NotNull
    public static EnumEntries<PusherApplicationType> getEntries() {
        return $ENTRIES;
    }

    @NotNull
    public final String convertToString() {
        return name();
    }
}
