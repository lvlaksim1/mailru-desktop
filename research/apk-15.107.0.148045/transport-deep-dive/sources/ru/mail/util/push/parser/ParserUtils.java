package ru.mail.util.push.parser;

import androidx.compose.runtime.internal.StabilityInferred;
import java.util.Map;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.cloud.stories.data.gson.parsers.BlockParser;
import ru.mail.util.log.Log;
import ru.mail.util.push.PushProcessor;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\b\u0004\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\"\u0010\u0006\u001a\u00020\u00072\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\t2\u0006\u0010\n\u001a\u00020\u0007J\u001c\u0010\u000b\u001a\u0004\u0018\u00010\u00072\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\tR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lru/mail/util/push/parser/ParserUtils;", "", "<init>", "()V", "LOG", "Lru/mail/util/log/Log;", "getOrThrow", "", "data", "", "key", "getOpenUrl", BlockParser.MAP_TYPE, "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ParserUtils {

    @NotNull
    public static final ParserUtils INSTANCE = new ParserUtils();

    @NotNull
    private static final Log LOG = Log.INSTANCE.getLog("ParserUtils");
    public static final int $stable = 8;

    private ParserUtils() {
    }

    @Nullable
    public final String getOpenUrl(@NotNull Map<String, String> map) {
        Intrinsics.checkNotNullParameter(map, "map");
        String str = map.get(PushProcessor.DATA_KEY_HUB_LINK);
        if (str == null) {
            return null;
        }
        try {
            return new JSONObject(str).getString("open");
        } catch (JSONException e10) {
            LOG.d("Can't parse ack from hub link: " + e10);
            return null;
        }
    }

    @NotNull
    public final String getOrThrow(@NotNull Map<String, String> data, @NotNull String key) throws NoSuchElementException {
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(key, "key");
        try {
            return (String) MapsKt.getValue(data, key);
        } catch (NoSuchElementException e10) {
            throw new NoSuchElementException("ParserUtils can't get \"" + key + "\" from push, " + e10);
        }
    }
}
