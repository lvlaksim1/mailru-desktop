package ru.mail.serverapi.retrofit.session;

import kotlin.Metadata;
import okhttp3.FormBody;
import okhttp3.Request;
import org.jetbrains.annotations.NotNull;
import ru.mail.kotlett.services.billing.analytics.Event;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH&¨\u0006\fÀ\u0006\u0003"}, d2 = {"Lru/mail/serverapi/retrofit/session/TornadoSession;", "", "applyToRequest", "", Event.Companion.Network.Fail.REQUEST_TAG, "Lokhttp3/Request$Builder;", "body", "Lokhttp3/FormBody$Builder;", "prepareSessionException", "Lru/mail/serverapi/retrofit/session/RequestSessionException;", "reason", "Lru/mail/serverapi/retrofit/session/RequestSessionException$Reason;", "server-api_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface TornadoSession {
    void applyToRequest(@NotNull Request.Builder request, @NotNull FormBody.Builder body);

    @NotNull
    RequestSessionException prepareSessionException(@NotNull RequestSessionException.Reason reason);
}
