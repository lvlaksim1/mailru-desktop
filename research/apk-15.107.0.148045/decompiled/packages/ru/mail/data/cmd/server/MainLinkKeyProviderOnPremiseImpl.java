package ru.mail.data.cmd.server;

import androidx.compose.runtime.internal.StabilityInferred;
import kotlin.Metadata;
import kotlin.text.Regex;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@StabilityInferred(parameters = 1)
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"Lru/mail/data/cmd/server/MainLinkKeyProviderOnPremiseImpl;", "Lru/mail/data/cmd/server/MainLinkKeyProvider;", "<init>", "()V", "getMainLinkKey", "Lkotlin/text/Regex;", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class MainLinkKeyProviderOnPremiseImpl implements MainLinkKeyProvider {
    public static final int $stable = 0;

    @Override // ru.mail.data.cmd.server.MainLinkKeyProvider
    @NotNull
    public Regex getMainLinkKey() {
        return new Regex("onpremise_discovery=(?:https|http)://");
    }
}
