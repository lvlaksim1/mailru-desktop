package ru.mail.mailbox.cmd;

import com.google.android.gms.ads.RequestConfiguration;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import ru.mail.cloud.stories.data.gson.parsers.BlockParser;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\b&\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J(\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0002\"\u0004\b\u0001\u0010\u00062\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u0002H\u00060\bH\u0016¨\u0006\t"}, d2 = {"Lru/mail/mailbox/cmd/ObservableFutureWithMapping;", "R", "Lru/mail/mailbox/cmd/ObservableFuture;", "<init>", "()V", BlockParser.MAP_TYPE, RequestConfiguration.MAX_AD_CONTENT_RATING_T, "mapper", "Lru/mail/mailbox/cmd/ObservableFuture$Mapper;", "command_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class ObservableFutureWithMapping<R> implements ObservableFuture<R> {
    @Override // ru.mail.mailbox.cmd.ObservableFuture
    @NotNull
    public <T> ObservableFuture<T> map(@NotNull ObservableFuture.Mapper<R, T> mapper) {
        Intrinsics.checkNotNullParameter(mapper, "mapper");
        return new MappedObservableFuture(this, mapper);
    }
}
