package ru.mail.data.cmd.server.parser;

import androidx.compose.runtime.internal.StabilityInferred;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lru/mail/data/cmd/server/parser/JsonSearchMsgParserNew;", "Lru/mail/data/cmd/server/parser/JsonMessageParserNew;", "account", "", "snippetLimit", "", "isColoredTagsOn", "", "<init>", "(Ljava/lang/String;IZ)V", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class JsonSearchMsgParserNew extends JsonMessageParserNew {
    public static final int $stable = 8;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JsonSearchMsgParserNew(@NotNull String account, int i10, boolean z10) {
        super(account, i10, z10);
        Intrinsics.checkNotNullParameter(account, "account");
    }
}
