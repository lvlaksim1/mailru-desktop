package ru.mail.data.cmd.server.parser;

import androidx.compose.runtime.internal.StabilityInferred;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.logic.betastate.BetaState;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 \b2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\bB\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\t"}, d2 = {"Lru/mail/data/cmd/server/parser/BetaStateParser;", "Lru/mail/data/cmd/server/parser/JSONParser;", "Lru/mail/logic/betastate/BetaState;", "<init>", "()V", "parse", "jsonObject", "Lorg/json/JSONObject;", "Companion", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class BetaStateParser extends JSONParser<BetaState> {

    @NotNull
    public static final String BUILD_NUMBER = "build";

    @NotNull
    public static final String EXPIRATION_DATE = "beta_time";
    public static final int $stable = 8;

    @Override // ru.mail.data.cmd.server.parser.JSONParser
    @NotNull
    public BetaState parse(@NotNull JSONObject jsonObject) throws JSONException {
        Intrinsics.checkNotNullParameter(jsonObject, "jsonObject");
        return new BetaState(jsonObject.getInt(BUILD_NUMBER), jsonObject.getLong(EXPIRATION_DATE));
    }
}
