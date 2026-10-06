package ru.mail.data.cmd.server;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0003H&¨\u0006\u0006À\u0006\u0003"}, d2 = {"Lru/mail/data/cmd/server/DomainResolver;", "", "resolve", "", "login", "extraDomainWithoutCheck", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface DomainResolver {
    @NotNull
    String resolve(@NotNull String login, @NotNull String extraDomainWithoutCheck);
}
