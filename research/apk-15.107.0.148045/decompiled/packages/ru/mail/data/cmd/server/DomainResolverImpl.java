package ru.mail.data.cmd.server;

import androidx.compose.runtime.internal.StabilityInferred;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import org.jetbrains.annotations.NotNull;
import ru.mail.auth.util.DomainUtils;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@StabilityInferred(parameters = 1)
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0005H\u0016¨\u0006\b"}, d2 = {"Lru/mail/data/cmd/server/DomainResolverImpl;", "Lru/mail/data/cmd/server/DomainResolver;", "<init>", "()V", "resolve", "", "login", "extraDomainWithoutCheck", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class DomainResolverImpl implements DomainResolver {
    public static final int $stable = 0;

    @Override // ru.mail.data.cmd.server.DomainResolver
    @NotNull
    public String resolve(@NotNull String login, @NotNull String extraDomainWithoutCheck) {
        Intrinsics.checkNotNullParameter(login, "login");
        Intrinsics.checkNotNullParameter(extraDomainWithoutCheck, "extraDomainWithoutCheck");
        if (extraDomainWithoutCheck.length() == 0) {
            extraDomainWithoutCheck = DomainUtils.getDomainWithoutCheck(login);
        }
        Intrinsics.checkNotNull(extraDomainWithoutCheck);
        return new Regex("https?://").replaceFirst(extraDomainWithoutCheck, "");
    }
}
