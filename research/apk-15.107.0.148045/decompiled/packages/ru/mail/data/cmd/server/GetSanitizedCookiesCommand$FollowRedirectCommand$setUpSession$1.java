package ru.mail.data.cmd.server;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import ru.mail.util.kotlin.cookie.MailCookie;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
final /* synthetic */ class GetSanitizedCookiesCommand$FollowRedirectCommand$setUpSession$1 extends FunctionReferenceImpl implements Function1<MailCookie, String> {
    public static final GetSanitizedCookiesCommand$FollowRedirectCommand$setUpSession$1 INSTANCE = new GetSanitizedCookiesCommand$FollowRedirectCommand$setUpSession$1();

    GetSanitizedCookiesCommand$FollowRedirectCommand$setUpSession$1() {
        super(1, MailCookie.class, "toNetscapeHeaderFormat", "toNetscapeHeaderFormat()Ljava/lang/String;", 0);
    }

    @Override // kotlin.jvm.functions.Function1
    public final String invoke(MailCookie p10) {
        Intrinsics.checkNotNullParameter(p10, "p0");
        return p10.toNetscapeHeaderFormat();
    }
}
