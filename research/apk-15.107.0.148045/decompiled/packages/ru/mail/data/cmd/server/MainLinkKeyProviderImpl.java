package ru.mail.data.cmd.server;

import androidx.compose.runtime.internal.StabilityInferred;
import kotlin.Metadata;
import kotlin.text.Regex;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@StabilityInferred(parameters = 1)
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\n\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lru/mail/data/cmd/server/MainLinkKeyProviderImpl;", "Lru/mail/data/cmd/server/MainLinkKeyProvider;", "useOnPremiseRegexp", "", "<init>", "(Z)V", "getMainLinkKey", "Lkotlin/text/Regex;", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class MainLinkKeyProviderImpl implements MainLinkKeyProvider {
    public static final int $stable = 0;
    private final boolean useOnPremiseRegexp;

    public MainLinkKeyProviderImpl(boolean z10) {
        this.useOnPremiseRegexp = z10;
    }

    @Override // ru.mail.data.cmd.server.MainLinkKeyProvider
    @Nullable
    public Regex getMainLinkKey() {
        if (this.useOnPremiseRegexp) {
            return new Regex("onpremise_discovery=(?:https|http)://");
        }
        return null;
    }
}
