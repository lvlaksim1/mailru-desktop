package ru.mail.help.di;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import ru.mail.logic.navigation.segue.ClickerLinkConstructor;
import ru.mail.util.log.Formats;
import ru.mail.util.log.InternalLogger;
import ru.mail.util.log.LogFilter;
import ru.mail.util.log.Logger;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eR\u001e\u0010\u0004\u001a\u0010\u0012\f\u0012\n \u0007*\u0004\u0018\u00010\u00060\u00060\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\b¨\u0006\u000f"}, d2 = {"Lru/mail/help/di/HelpInternalLogger;", "", "<init>", "()V", "listOfConstraints", "", "Lru/mail/util/log/Formats$ParamFormat;", "kotlin.jvm.PlatformType", "[Lru/mail/util/log/Formats$ParamFormat;", "provideInternalLogger", "Lru/mail/util/log/InternalLogger;", "appId", "", "logger", "Lru/mail/util/log/Logger;", "help_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class HelpInternalLogger {

    @NotNull
    public static final HelpInternalLogger INSTANCE = new HelpInternalLogger();

    @NotNull
    private static final Formats.ParamFormat[] listOfConstraints = {Formats.newJsonFormat("content"), Formats.newUrlFormat("content"), Formats.newUrlEncodedJsonFormat("content"), Formats.newJsonFormat("title"), Formats.newUrlFormat("title"), Formats.newUrlEncodedJsonFormat("title"), Formats.newUrlFormat("token"), Formats.newJsonFormat("token"), Formats.newUrlFormat("password"), Formats.newJsonFormat("password"), Formats.newUrlFormat(ClickerLinkConstructor.AUTOGEN_TOKEN), Formats.newJsonFormat(ClickerLinkConstructor.AUTOGEN_TOKEN), Formats.newJsonFormat("access_token"), Formats.newUrlFormat("access_token"), Formats.newUrlFormat("Mpop")};

    private HelpInternalLogger() {
    }

    @NotNull
    public final InternalLogger provideInternalLogger(@NotNull String appId, @NotNull Logger logger) {
        Intrinsics.checkNotNullParameter(appId, "appId");
        Intrinsics.checkNotNullParameter(logger, "logger");
        InternalLogger.Companion companion = InternalLogger.INSTANCE;
        Formats.ParamFormat[] paramFormatArr = listOfConstraints;
        return companion.createLogger(appId, logger, new LogFilter((Formats.ParamFormat[]) Arrays.copyOf(paramFormatArr, paramFormatArr.length)));
    }
}
