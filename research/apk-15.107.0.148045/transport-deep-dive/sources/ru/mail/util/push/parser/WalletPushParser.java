package ru.mail.util.push.parser;

import androidx.compose.runtime.internal.StabilityInferred;
import java.util.Date;
import java.util.Map;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.util.log.Log;
import ru.mail.util.push.wallet.WalletNotificationPush;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
@StabilityInferred(parameters = 1)
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0007\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\"\u0010\u0004\u001a\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\nJ\u001c\u0010\u000b\u001a\u0004\u0018\u00010\b2\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u0007¨\u0006\r"}, d2 = {"Lru/mail/util/push/parser/WalletPushParser;", "", "<init>", "()V", "parse", "Lru/mail/util/push/wallet/WalletNotificationPush;", "data", "", "", "eventId", "", "getMlReminder", "Companion", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class WalletPushParser {
    public static final int $stable = 0;

    @NotNull
    private static final String DATA_KEY_AMP_TOKEN = "amp_token";

    @NotNull
    private static final String DATA_KEY_WALLET_EVENT_TIME = "event_start";

    @NotNull
    private static final String DATA_KEY_WALLET_EVENT_URL = "event_url";

    @NotNull
    private static final String DATA_KEY_WALLET_LOGIN = "login";

    @NotNull
    private static final String DATA_KEY_WALLET_ML_SUBTYPE = "ml_subtype";

    @NotNull
    private static final String DATA_KEY_WALLET_ML_TYPE = "ml_type";

    @NotNull
    private static final String DATA_KEY_WALLET_MSG = "msg";

    @NotNull
    private static final String DATA_KEY_WALLET_SUMMARY_TEXT = "summary";

    @NotNull
    private static final String DATA_KEY_WALLET_TITLE = "title";

    @NotNull
    private static final String DL_KEY = "dl";

    @NotNull
    private static final String ML_REMINDER_KEY = "ml_reminder";

    @NotNull
    private static final Log LOG = Log.INSTANCE.getLog("WalletPushParser");

    @Nullable
    public final String getMlReminder(@NotNull Map<String, String> data) {
        Intrinsics.checkNotNullParameter(data, "data");
        try {
            return (String) MapsKt.getValue(data, ML_REMINDER_KEY);
        } catch (Exception e10) {
            LOG.e("Can't parse ml_reminder_key", e10);
            return null;
        }
    }

    @NotNull
    public final WalletNotificationPush parse(@NotNull Map<String, String> data, int eventId) throws NoSuchElementException {
        Intrinsics.checkNotNullParameter(data, "data");
        ParserUtils parserUtils = ParserUtils.INSTANCE;
        String orThrow = parserUtils.getOrThrow(data, DATA_KEY_AMP_TOKEN);
        String orThrow2 = parserUtils.getOrThrow(data, "login");
        String orThrow3 = parserUtils.getOrThrow(data, "title");
        String orThrow4 = parserUtils.getOrThrow(data, "msg");
        String orThrow5 = parserUtils.getOrThrow(data, DATA_KEY_WALLET_EVENT_URL);
        String openUrl = parserUtils.getOpenUrl(data);
        String str = data.get(DL_KEY);
        if (str == null) {
            str = "";
        }
        String str2 = str;
        String str3 = data.get("summary");
        Date eventTime = TimeParserUtils.INSTANCE.getEventTime(data.get(DATA_KEY_WALLET_EVENT_TIME));
        MlPushParserUtils mlPushParserUtils = MlPushParserUtils.INSTANCE;
        return new WalletNotificationPush(orThrow, eventId, orThrow2, orThrow3, orThrow4, orThrow5, str2, mlPushParserUtils.getMLType(data.get(DATA_KEY_WALLET_ML_TYPE)), mlPushParserUtils.getMLSubtype(data.get(DATA_KEY_WALLET_ML_SUBTYPE)), eventTime, openUrl, str3, getMlReminder(data));
    }
}
