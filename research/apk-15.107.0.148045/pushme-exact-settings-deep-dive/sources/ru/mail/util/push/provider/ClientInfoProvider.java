package ru.mail.util.push.provider;

import java.util.Map;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003H&¨\u0006\u0005À\u0006\u0003"}, d2 = {"Lru/mail/util/push/provider/ClientInfoProvider;", "", "getClientInfo", "", "", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface ClientInfoProvider {
    @NotNull
    Map<String, String> getClientInfo();
}
