package ru.mail.data.cmd.server.pusher;

import android.content.Context;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.serverapi.DependentStatusCmd;
import ru.mail.serverapi.FolderState;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lru/mail/data/cmd/server/pusher/SendPushSettingsCmd;", "Lru/mail/serverapi/DependentStatusCmd;", "context", "Landroid/content/Context;", "dependentClasses", "Ljava/lang/Class;", "login", "", "folderState", "Lru/mail/serverapi/FolderState;", "<init>", "(Landroid/content/Context;Ljava/lang/Class;Ljava/lang/String;Lru/mail/serverapi/FolderState;)V", "mail-cmd_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class SendPushSettingsCmd extends DependentStatusCmd {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SendPushSettingsCmd(@NotNull Context context, @NotNull Class<?> dependentClasses, @Nullable String str, @NotNull FolderState folderState) {
        super(context, dependentClasses, str, folderState);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(dependentClasses, "dependentClasses");
        Intrinsics.checkNotNullParameter(folderState, "folderState");
    }
}
