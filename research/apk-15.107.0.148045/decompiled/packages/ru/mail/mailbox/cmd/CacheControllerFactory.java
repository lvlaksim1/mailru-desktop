package ru.mail.mailbox.cmd;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u000e\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00030\u0005H&¨\u0006\u0006À\u0006\u0003"}, d2 = {"Lru/mail/mailbox/cmd/CacheControllerFactory;", "", "create", "Lru/mail/mailbox/cmd/CacheController;", "controllerClass", "Ljava/lang/Class;", "command_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface CacheControllerFactory {
    @NotNull
    CacheController create(@NotNull Class<? extends CacheController> controllerClass);
}
