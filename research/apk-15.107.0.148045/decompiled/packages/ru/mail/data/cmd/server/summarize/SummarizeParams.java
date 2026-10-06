package ru.mail.data.cmd.server.summarize;

import android.content.Context;
import com.huawei.hms.push.constant.RemoteMessageConst;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.http.protocol.HTTP;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.auth.request.AccountInfo;
import ru.mail.network.Param;
import ru.mail.serverapi.ServerCommandBaseParams;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\t\u0018\u00002\u00020\u0001B3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\n\u0010\u000bR\u0016\u0010\u0006\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0016\u0010\u0007\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0018\u0010\t\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\r¨\u0006\u0011"}, d2 = {"Lru/mail/data/cmd/server/summarize/SummarizeParams;", "Lru/mail/serverapi/ServerCommandBaseParams;", "login", "", "context", "Landroid/content/Context;", RemoteMessageConst.MSGID, HTTP.CHUNK_CODING, "", "group", "<init>", "(Ljava/lang/String;Landroid/content/Context;Ljava/lang/String;ZLjava/lang/String;)V", "getMsgId", "()Ljava/lang/String;", "getChunked", "()Z", "getGroup", "mail-app-core_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SummarizeParams extends ServerCommandBaseParams {

    @Param(name = "streaming")
    private final boolean chunked;

    @Param(name = "group")
    @Nullable
    private final String group;

    @Param(name = "id")
    @NotNull
    private final String msgId;

    public /* synthetic */ SummarizeParams(String str, Context context, String str2, boolean z10, String str3, int i10, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, context, str2, z10, (i10 & 16) != 0 ? null : str3);
    }

    public final boolean getChunked() {
        return this.chunked;
    }

    @Nullable
    public final String getGroup() {
        return this.group;
    }

    @NotNull
    public final String getMsgId() {
        return this.msgId;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SummarizeParams(@NotNull String login, @NotNull Context context, @NotNull String msgId, boolean z10, @Nullable String str) {
        super(new AccountInfo(login, context), null);
        Intrinsics.checkNotNullParameter(login, "login");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(msgId, "msgId");
        this.msgId = msgId;
        this.chunked = z10;
        this.group = str;
    }
}
