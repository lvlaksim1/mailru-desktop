package ru.mail.serverapi.retrofit.session;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&¨\u0006\u0006À\u0006\u0003"}, d2 = {"Lru/mail/serverapi/retrofit/session/SessionCreator;", "", "create", "Lru/mail/serverapi/retrofit/session/TornadoSession;", "login", "", "server-api_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface SessionCreator {
    @NotNull
    TornadoSession create(@Nullable String login) throws BadSessionException;
}
