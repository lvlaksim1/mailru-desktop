package ru.mail.auth.request;

import android.content.Context;
import java.util.Map;
import ru.mail.network.HostProvider;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
public class AuthorizeTokenRequest extends AuthorizeRequest<AuthorizeTokenCommand> {
    public AuthorizeTokenRequest(Context context, String str, Map<String, String> map, Map<String, String> map2, HostProvider hostProvider, boolean z10, boolean z11) {
        super(context, new AuthorizeTokenCommand(context, str, map, map2, hostProvider, z10, z11));
    }
}
