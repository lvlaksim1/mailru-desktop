package ru.mail.util.push.parser;

import androidx.compose.runtime.internal.StabilityInferred;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.util.log.Log;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0005R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\r"}, d2 = {"Lru/mail/util/push/parser/TimeParserUtils;", "", "<init>", "()V", "DATE_PATTERN", "", "LOG", "Lru/mail/util/log/Log;", "getLOG", "()Lru/mail/util/log/Log;", "getEventTime", "Ljava/util/Date;", "rfc3339WithoutTZ", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class TimeParserUtils {

    @NotNull
    public static final String DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss";

    @NotNull
    public static final TimeParserUtils INSTANCE = new TimeParserUtils();

    @NotNull
    private static final Log LOG = Log.INSTANCE.getLog("TimeParserUtils");
    public static final int $stable = 8;

    private TimeParserUtils() {
    }

    @Nullable
    public final Date getEventTime(@Nullable String rfc3339WithoutTZ) {
        if (rfc3339WithoutTZ == null) {
            LOG.w("event_time is null");
            return null;
        }
        try {
            return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault()).parse(rfc3339WithoutTZ);
        } catch (ParseException e10) {
            LOG.w("Can't parse event_time", e10);
            return null;
        }
    }

    @NotNull
    public final Log getLOG() {
        return LOG;
    }
}
