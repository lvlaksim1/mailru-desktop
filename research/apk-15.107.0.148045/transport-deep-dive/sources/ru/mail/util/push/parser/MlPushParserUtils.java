package ru.mail.util.push.parser;

import androidx.compose.runtime.internal.StabilityInferred;
import com.huawei.hms.framework.network.grs.GrsBaseInfo;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.util.push.calendar.MLPushSubType;
import ru.mail.util.push.calendar.MLPushType;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
@StabilityInferred(parameters = 1)
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007J\u0010\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u0007¨\u0006\u000b"}, d2 = {"Lru/mail/util/push/parser/MlPushParserUtils;", "", "<init>", "()V", "getMLType", "Lru/mail/util/push/calendar/MLPushType;", "mlType", "", "getMLSubtype", "Lru/mail/util/push/calendar/MLPushSubType;", "subtype", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class MlPushParserUtils {
    public static final int $stable = 0;

    @NotNull
    public static final MlPushParserUtils INSTANCE = new MlPushParserUtils();

    private MlPushParserUtils() {
    }

    @NotNull
    public final MLPushSubType getMLSubtype(@Nullable String subtype) {
        return subtype == null ? new MLPushSubType.Unknown(GrsBaseInfo.CountryCodeSource.UNKNOWN) : MLPushSubType.INSTANCE.from(subtype);
    }

    @NotNull
    public final MLPushType getMLType(@Nullable String mlType) {
        return mlType == null ? new MLPushType.Unknown(GrsBaseInfo.CountryCodeSource.UNKNOWN) : MLPushType.INSTANCE.from(mlType);
    }
}
