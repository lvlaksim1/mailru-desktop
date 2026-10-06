package ru.mail.data.cmd.server.parser;

import androidx.compose.runtime.internal.StabilityInferred;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.logic.content.MetaThreadEnableState;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"Lru/mail/data/cmd/server/parser/MetaThreadsSettingsParser;", "Lru/mail/data/cmd/server/parser/JSONParser;", "Lru/mail/logic/content/MetaThreadEnableState;", "<init>", "()V", "parse", "jsonObject", "Lorg/json/JSONObject;", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class MetaThreadsSettingsParser extends JSONParser<MetaThreadEnableState> {
    public static final int $stable = 8;

    @Override // ru.mail.data.cmd.server.parser.JSONParser
    @NotNull
    public MetaThreadEnableState parse(@NotNull JSONObject jsonObject) throws JSONException {
        Intrinsics.checkNotNullParameter(jsonObject, "jsonObject");
        return new MetaThreadEnableState(jsonObject.getLong("folder_id"), MetaThreadEnableState.State.INSTANCE.from(jsonObject.getInt("state"), MetaThreadEnableState.State.DISABLED));
    }
}
