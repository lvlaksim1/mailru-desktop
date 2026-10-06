package ru.mail.serverapi.retrofit.session;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import ru.mail.logic.navigation.segue.ClickerLinkConstructor;
import ru.mail.util.log.Formats;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lru/mail/serverapi/retrofit/session/TornadoSessionSensitiveFilters;", "", "<init>", "()V", "filters", "", "Lru/mail/util/log/Formats$ParamFormat;", "getFilters", "()Ljava/util/List;", "server-api_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class TornadoSessionSensitiveFilters {

    @NotNull
    public static final TornadoSessionSensitiveFilters INSTANCE = new TornadoSessionSensitiveFilters();

    @NotNull
    private static final List<Formats.ParamFormat> filters;

    static {
        Formats.ParamFormat paramFormatNewUrlFormat = Formats.newUrlFormat("token");
        Intrinsics.checkNotNullExpressionValue(paramFormatNewUrlFormat, "newUrlFormat(...)");
        Formats.ParamFormat paramFormatNewJsonFormat = Formats.newJsonFormat("token");
        Intrinsics.checkNotNullExpressionValue(paramFormatNewJsonFormat, "newJsonFormat(...)");
        Formats.ParamFormat paramFormatNewUrlFormat2 = Formats.newUrlFormat("password");
        Intrinsics.checkNotNullExpressionValue(paramFormatNewUrlFormat2, "newUrlFormat(...)");
        Formats.ParamFormat paramFormatNewJsonFormat2 = Formats.newJsonFormat("password");
        Intrinsics.checkNotNullExpressionValue(paramFormatNewJsonFormat2, "newJsonFormat(...)");
        Formats.ParamFormat paramFormatNewUrlFormat3 = Formats.newUrlFormat(ClickerLinkConstructor.AUTOGEN_TOKEN);
        Intrinsics.checkNotNullExpressionValue(paramFormatNewUrlFormat3, "newUrlFormat(...)");
        Formats.ParamFormat paramFormatNewJsonFormat3 = Formats.newJsonFormat(ClickerLinkConstructor.AUTOGEN_TOKEN);
        Intrinsics.checkNotNullExpressionValue(paramFormatNewJsonFormat3, "newJsonFormat(...)");
        Formats.ParamFormat paramFormatNewJsonFormat4 = Formats.newJsonFormat("access_token");
        Intrinsics.checkNotNullExpressionValue(paramFormatNewJsonFormat4, "newJsonFormat(...)");
        Formats.ParamFormat paramFormatNewUrlFormat4 = Formats.newUrlFormat("access_token");
        Intrinsics.checkNotNullExpressionValue(paramFormatNewUrlFormat4, "newUrlFormat(...)");
        Formats.ParamFormat paramFormatNewUrlFormat5 = Formats.newUrlFormat("Mpop");
        Intrinsics.checkNotNullExpressionValue(paramFormatNewUrlFormat5, "newUrlFormat(...)");
        filters = CollectionsKt.listOf((Object[]) new Formats.ParamFormat[]{paramFormatNewUrlFormat, paramFormatNewJsonFormat, paramFormatNewUrlFormat2, paramFormatNewJsonFormat2, paramFormatNewUrlFormat3, paramFormatNewJsonFormat3, paramFormatNewJsonFormat4, paramFormatNewUrlFormat4, paramFormatNewUrlFormat5});
    }

    private TornadoSessionSensitiveFilters() {
    }

    @NotNull
    public final List<Formats.ParamFormat> getFilters() {
        return filters;
    }
}
