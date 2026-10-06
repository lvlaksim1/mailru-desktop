package ru.mail.data.cmd.server.parser;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.data.entities.OrderItemImpl;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\u00022\u0006\u0010\b\u001a\u00020\tH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lru/mail/data/cmd/server/parser/OrderItemParser;", "Lru/mail/data/cmd/server/parser/JSONParser;", "Lru/mail/data/entities/OrderItemImpl;", "account", "", "<init>", "(Ljava/lang/String;)V", "parse", "json", "Lorg/json/JSONObject;", "message_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nOrderItemParser.kt\nKotlin\n*S Kotlin\n*F\n+ 1 OrderItemParser.kt\nru/mail/data/cmd/server/parser/OrderItemParser\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,20:1\n1#2:21\n*E\n"})
public final class OrderItemParser extends JSONParser<OrderItemImpl> {

    @NotNull
    private final String account;

    public OrderItemParser(@NotNull String account) {
        Intrinsics.checkNotNullParameter(account, "account");
        this.account = account;
    }

    @Override // ru.mail.data.cmd.server.parser.JSONParser
    @Nullable
    public OrderItemImpl parse(@NotNull JSONObject json) throws JSONException {
        Intrinsics.checkNotNullParameter(json, "json");
        String strOptString = json.optString("name");
        Intrinsics.checkNotNull(strOptString);
        if (strOptString.length() <= 0) {
            strOptString = null;
        }
        if (strOptString == null) {
            return null;
        }
        String str = this.account;
        String strOptString2 = json.optString("href");
        if (strOptString2 == null) {
            strOptString2 = "";
        }
        String strOptString3 = json.optString("img");
        return new OrderItemImpl(str, strOptString, strOptString2, strOptString3 != null ? strOptString3 : "");
    }
}
