package ru.mail.data.cmd.server;

import android.accounts.Account;
import android.content.Context;
import androidx.compose.runtime.internal.StabilityInferred;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.auth.AccountManagerWrapper;
import ru.mail.auth.Authenticator;
import ru.mail.data.entities.MailboxProfile;
import ru.mail.sdk.BuildConfigVariablesHolder;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lru/mail/data/cmd/server/UserFiltersStorageImpl;", "Lru/mail/data/cmd/server/UserFiltersStorage;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "saveHasFilters", "", "login", "", "hasFilters", "", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class UserFiltersStorageImpl implements UserFiltersStorage {
    public static final int $stable = 8;

    @NotNull
    private final Context context;

    public UserFiltersStorageImpl(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    @Override // ru.mail.data.cmd.server.UserFiltersStorage
    public void saveHasFilters(@Nullable String login, boolean hasFilters) {
        AccountManagerWrapper accountManagerWrapper = Authenticator.getAccountManagerWrapper(this.context);
        Intrinsics.checkNotNull(login);
        accountManagerWrapper.setUserData(new Account(login, BuildConfigVariablesHolder.accountType), MailboxProfile.ACCOUNT_HAS_FILTERS, String.valueOf(hasFilters));
    }
}
