package com.vk.accountmanager.data;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.RequiresApi;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import com.vk.accountmanager.data.AccountManagerRepositoryImpl;
import com.vk.accountmanager.domain.AccountManagerRepository;
import com.vk.api.sdk.auth.AccountProfileType;
import com.vk.dto.common.id.UserId;
import com.vk.dto.common.id.UserIdKt;
import com.vk.superapp.core.extensions.ContextExtKt;
import com.vk.superapp.core.types.ManifestMetadataKt;
import com.vk.superapp.core.utils.WebLogger;
import com.vk.superapp.statinteractor.api.domain.interactor.SessionStatInteractor;
import com.vk.usersstore.blockstore.deletereceiver.BlockstoreDeleteReceiver;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.auth.MailLoginFragment;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes.dex */
@RequiresApi(22)
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u000e\b\u0001\u0018\u0000 +2\u00020\u0001:\u0001+B!\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\n\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\n\u0010\u000bJ\u0019\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\r\u001a\u00020\fH\u0017¢\u0006\u0004\b\u000f\u0010\u0010J\u0017\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0012\u001a\u00020\u0011H\u0017¢\u0006\u0004\b\u0014\u0010\u0015J\u0019\u0010\u0016\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0012\u001a\u00020\u0011H\u0017¢\u0006\u0004\b\u0016\u0010\u0017J\u0019\u0010\u0018\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0012\u001a\u00020\u0011H\u0016¢\u0006\u0004\b\u0018\u0010\u0017J\u0015\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\f0\u0019H\u0017¢\u0006\u0004\b\u001a\u0010\u001bJ\u0015\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\f0\u0019H\u0016¢\u0006\u0004\b\u001c\u0010\u001bJ\u0019\u0010\u001d\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\r\u001a\u00020\fH\u0017¢\u0006\u0004\b\u001d\u0010\u0010J\u0017\u0010 \u001a\u00020\u000e2\u0006\u0010\u001f\u001a\u00020\u001eH\u0007¢\u0006\u0004\b \u0010!R\u001a\u0010\u0007\u001a\u00020\u00068\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\"\u0010#\u001a\u0004\b$\u0010%R\u001b\u0010*\u001a\u00020\u001e8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b&\u0010'\u001a\u0004\b(\u0010)¨\u0006,"}, d2 = {"Lcom/vk/accountmanager/data/AccountManagerRepositoryImpl;", "Lcom/vk/accountmanager/domain/AccountManagerRepository;", "Landroid/content/Context;", "context", "Lcom/vk/superapp/statinteractor/api/domain/interactor/SessionStatInteractor;", "statInteractor", "Landroid/accounts/AccountManager;", "accountManager", "<init>", "(Landroid/content/Context;Lcom/vk/superapp/statinteractor/api/domain/interactor/SessionStatInteractor;Landroid/accounts/AccountManager;)V", "appContext", "()Landroid/content/Context;", "Lcom/vk/accountmanager/data/AccountManagerData;", "data", "Landroid/accounts/Account;", "addAccountToAccountManager", "(Lcom/vk/accountmanager/data/AccountManagerData;)Landroid/accounts/Account;", "Lcom/vk/dto/common/id/UserId;", BlockstoreDeleteReceiver.PARAM_USER_ID, "", "clearAccount", "(Lcom/vk/dto/common/id/UserId;)Z", "getAccountData", "(Lcom/vk/dto/common/id/UserId;)Lcom/vk/accountmanager/data/AccountManagerData;", "getAccountDataUnsafe", "", "getAllAccounts", "()Ljava/util/List;", "getAllAccountsUnsafe", "updateData", "", "username", "createAccountByUsername", "(Ljava/lang/String;)Landroid/accounts/Account;", "lpmireganamtnuoccakvmocb", "Landroid/accounts/AccountManager;", "getAccountManager", "()Landroid/accounts/AccountManager;", "lpmireganamtnuoccakvmocc", "Lkotlin/Lazy;", "getAccountType", "()Ljava/lang/String;", MailLoginFragment.EXTRA_ACCOUNT_TYPE, "Companion", "impl_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nAccountManagerRepositoryImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AccountManagerRepositoryImpl.kt\ncom/vk/accountmanager/data/AccountManagerRepositoryImpl\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,252:1\n1869#2,2:253\n1563#2:259\n1634#2,3:260\n1#3:255\n3829#4:256\n4344#4,2:257\n3829#4:263\n4344#4,2:264\n*S KotlinDebug\n*F\n+ 1 AccountManagerRepositoryImpl.kt\ncom/vk/accountmanager/data/AccountManagerRepositoryImpl\n*L\n94#1:253,2\n140#1:259\n140#1:260,3\n139#1:256\n139#1:257,2\n196#1:263\n196#1:264,2\n*E\n"})
public final class AccountManagerRepositoryImpl implements AccountManagerRepository {

    @NotNull
    public static final String ACCESS_TOKEN_ARG = "access_token";

    @NotNull
    public static final String ACCOUNT_PROFILE_TYPE_ARG = "account_profile_type";

    @NotNull
    public static final String CREATED_ARG = "created";

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    @NotNull
    public static final String EXCHANGE_TOKEN_ARG = "exchange_token";

    @NotNull
    public static final String EXPIRES_IN_ARG = "expires_in";

    @NotNull
    public static final String MASTER_ACCOUNT_ID_ARG = "master_account_id";

    @NotNull
    public static final String ORDINAL_ARG = "ordinal";

    @NotNull
    public static final String SECRET_ARG = "secret";

    @NotNull
    public static final String UID_ARG = "uid";

    @NotNull
    private final Context lpmireganamtnuoccakvmoca;

    /* JADX INFO: renamed from: lpmireganamtnuoccakvmocb, reason: from kotlin metadata */
    @NotNull
    private final AccountManager accountManager;

    /* JADX INFO: renamed from: lpmireganamtnuoccakvmocc, reason: from kotlin metadata */
    @NotNull
    private final Lazy accountType;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0013\b\u0087\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087T¢\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0003R\u0016\u0010\u0007\u001a\u00020\u00058\u0006X\u0087T¢\u0006\b\n\u0000\u0012\u0004\b\b\u0010\u0003R\u0016\u0010\t\u001a\u00020\u00058\u0006X\u0087T¢\u0006\b\n\u0000\u0012\u0004\b\n\u0010\u0003R\u0016\u0010\u000b\u001a\u00020\u00058\u0006X\u0087T¢\u0006\b\n\u0000\u0012\u0004\b\f\u0010\u0003R\u0016\u0010\r\u001a\u00020\u00058\u0006X\u0087T¢\u0006\b\n\u0000\u0012\u0004\b\u000e\u0010\u0003R\u0016\u0010\u000f\u001a\u00020\u00058\u0006X\u0087T¢\u0006\b\n\u0000\u0012\u0004\b\u0010\u0010\u0003R\u0016\u0010\u0011\u001a\u00020\u00058\u0006X\u0087T¢\u0006\b\n\u0000\u0012\u0004\b\u0012\u0010\u0003R\u0016\u0010\u0013\u001a\u00020\u00058\u0006X\u0087T¢\u0006\b\n\u0000\u0012\u0004\b\u0014\u0010\u0003R\u0016\u0010\u0015\u001a\u00020\u00058\u0006X\u0087T¢\u0006\b\n\u0000\u0012\u0004\b\u0016\u0010\u0003R\u000e\u0010\u0017\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/vk/accountmanager/data/AccountManagerRepositoryImpl$Companion;", "", "<init>", "()V", "UID_ARG", "", "getUID_ARG$annotations", "ACCESS_TOKEN_ARG", "getACCESS_TOKEN_ARG$annotations", "SECRET_ARG", "getSECRET_ARG$annotations", "EXPIRES_IN_ARG", "getEXPIRES_IN_ARG$annotations", "CREATED_ARG", "getCREATED_ARG$annotations", "ORDINAL_ARG", "getORDINAL_ARG$annotations", "EXCHANGE_TOKEN_ARG", "getEXCHANGE_TOKEN_ARG$annotations", "ACCOUNT_PROFILE_TYPE_ARG", "getACCOUNT_PROFILE_TYPE_ARG$annotations", "MASTER_ACCOUNT_ID_ARG", "getMASTER_ACCOUNT_ID_ARG$annotations", "METADATA_ACCOUNT_TYPE", "impl_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    @VisibleForTesting(otherwise = 2)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @VisibleForTesting(otherwise = 2)
        public static /* synthetic */ void getACCESS_TOKEN_ARG$annotations() {
        }

        @VisibleForTesting(otherwise = 2)
        public static /* synthetic */ void getACCOUNT_PROFILE_TYPE_ARG$annotations() {
        }

        @VisibleForTesting(otherwise = 2)
        public static /* synthetic */ void getCREATED_ARG$annotations() {
        }

        @VisibleForTesting(otherwise = 2)
        public static /* synthetic */ void getEXCHANGE_TOKEN_ARG$annotations() {
        }

        @VisibleForTesting(otherwise = 2)
        public static /* synthetic */ void getEXPIRES_IN_ARG$annotations() {
        }

        @VisibleForTesting(otherwise = 2)
        public static /* synthetic */ void getMASTER_ACCOUNT_ID_ARG$annotations() {
        }

        @VisibleForTesting(otherwise = 2)
        public static /* synthetic */ void getORDINAL_ARG$annotations() {
        }

        @VisibleForTesting(otherwise = 2)
        public static /* synthetic */ void getSECRET_ARG$annotations() {
        }

        @VisibleForTesting(otherwise = 2)
        public static /* synthetic */ void getUID_ARG$annotations() {
        }
    }

    public AccountManagerRepositoryImpl(@NotNull Context context, @NotNull SessionStatInteractor statInteractor, @NotNull AccountManager accountManager) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(statInteractor, "statInteractor");
        Intrinsics.checkNotNullParameter(accountManager, "accountManager");
        this.lpmireganamtnuoccakvmoca = context;
        this.accountManager = accountManager;
        this.accountType = LazyKt.lazy(new Function0() { // from class: s0.a
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return AccountManagerRepositoryImpl.lpmireganamtnuoccakvmoca(this.f103841a);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String lpmireganamtnuoccakvmoca(AccountManagerRepositoryImpl accountManagerRepositoryImpl) {
        return ManifestMetadataKt.nonEmptyString(ContextExtKt.getMetadata(accountManagerRepositoryImpl.getLpmireganamtnuoccakvmoca()), "com.vk.accountmanager.id");
    }

    private final AccountProfileType lpmireganamtnuoccakvmocb(Account account) {
        try {
            String userData = getAccountManager().getUserData(account, ACCOUNT_PROFILE_TYPE_ARG);
            Intrinsics.checkNotNullExpressionValue(userData, "getUserData(...)");
            AccountProfileType accountProfileTypeFindByCode = AccountProfileType.INSTANCE.findByCode(StringsKt.toIntOrNull(userData));
            return accountProfileTypeFindByCode == null ? AccountProfileType.NORMAL : accountProfileTypeFindByCode;
        } catch (Exception unused) {
            return AccountProfileType.NORMAL;
        }
    }

    @Override // com.vk.accountmanager.domain.AccountManagerRepository
    @WorkerThread
    @Nullable
    public synchronized Account addAccountToAccountManager(@NotNull AccountManagerData data) {
        Account accountCreateAccountByUsername;
        try {
            Intrinsics.checkNotNullParameter(data, "data");
            try {
                accountCreateAccountByUsername = createAccountByUsername(data.getUsername());
                Bundle bundle = new Bundle(9);
                bundle.putString("uid", String.valueOf(data.getUid().getValue()));
                bundle.putString("access_token", data.getAccessToken());
                bundle.putString(SECRET_ARG, data.getSecret());
                bundle.putString("expires_in", String.valueOf(data.getExpiresInSec()));
                bundle.putString(CREATED_ARG, String.valueOf(data.getCreatedMs()));
                bundle.putString(ORDINAL_ARG, String.valueOf(data.getOrdinal()));
                bundle.putString("exchange_token", data.getExchangeToken());
                bundle.putString(ACCOUNT_PROFILE_TYPE_ARG, String.valueOf(data.getAccountProfileType().getCode()));
                UserId masterAccountId = data.getMasterAccountId();
                bundle.putString(MASTER_ACCOUNT_ID_ARG, String.valueOf(masterAccountId != null ? Long.valueOf(masterAccountId.getValue()) : null));
                clearAccount(data.getUid());
                getAccountManager().addAccountExplicitly(accountCreateAccountByUsername, null, bundle);
            } catch (Exception e10) {
                WebLogger.INSTANCE.e(e10);
                return null;
            }
        } catch (Throwable th2) {
            throw th2;
        }
        return accountCreateAccountByUsername;
    }

    @Override // com.vk.accountmanager.domain.AccountManagerRepository
    @NotNull
    /* JADX INFO: renamed from: appContext, reason: from getter */
    public Context getLpmireganamtnuoccakvmoca() {
        return this.lpmireganamtnuoccakvmoca;
    }

    @Override // com.vk.accountmanager.domain.AccountManagerRepository
    @WorkerThread
    public synchronized boolean clearAccount(@NotNull UserId userId) {
        Account account;
        Long longOrNull;
        try {
            Intrinsics.checkNotNullParameter(userId, "userId");
            try {
                Account[] accountsByTypeForPackage = getAccountManager().getAccountsByTypeForPackage(getAccountType(), getLpmireganamtnuoccakvmoca().getPackageName());
                Intrinsics.checkNotNullExpressionValue(accountsByTypeForPackage, "getAccountsByTypeForPackage(...)");
                int length = accountsByTypeForPackage.length;
                int i10 = 0;
                while (true) {
                    account = null;
                    if (i10 < length) {
                        Account account2 = accountsByTypeForPackage[i10];
                        String userData = getAccountManager().getUserData(account2, "uid");
                        if (!Intrinsics.areEqual(new UserId((userData == null || (longOrNull = StringsKt.toLongOrNull(userData)) == null) ? UserId.DEFAULT.getValue() : longOrNull.longValue()), userId)) {
                            String name = account2.name;
                            Intrinsics.checkNotNullExpressionValue(name, "name");
                            Long longOrNull2 = StringsKt.toLongOrNull(name);
                            if (!Intrinsics.areEqual(longOrNull2 != null ? UserIdKt.toUserId(longOrNull2.longValue()) : null, userId)) {
                                i10++;
                            }
                        }
                        account = account2;
                        break;
                    }
                    break;
                }
                Iterator<T> it = lpmireganamtnuoccakvmoca(account, accountsByTypeForPackage).iterator();
                while (it.hasNext()) {
                    getAccountManager().removeAccountExplicitly((Account) it.next());
                }
                if (account != null) {
                    return getAccountManager().removeAccountExplicitly(account);
                }
                return false;
            } catch (Exception e10) {
                WebLogger.INSTANCE.e(e10);
                return false;
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }

    @VisibleForTesting(otherwise = 2)
    @NotNull
    public final Account createAccountByUsername(@NotNull String username) {
        Intrinsics.checkNotNullParameter(username, "username");
        return new Account(username, getAccountType());
    }

    @Override // com.vk.accountmanager.domain.AccountManagerRepository
    @WorkerThread
    @Nullable
    public synchronized AccountManagerData getAccountData(@NotNull UserId userId) {
        Intrinsics.checkNotNullParameter(userId, "userId");
        return getAccountDataUnsafe(userId);
    }

    @Override // com.vk.accountmanager.domain.AccountManagerRepository
    @Nullable
    public AccountManagerData getAccountDataUnsafe(@NotNull UserId userId) {
        Integer intOrNull;
        Long longOrNull;
        Integer intOrNull2;
        Intrinsics.checkNotNullParameter(userId, "userId");
        try {
            Account accountLpmireganamtnuoccakvmoca = lpmireganamtnuoccakvmoca(userId);
            if (accountLpmireganamtnuoccakvmoca == null) {
                return null;
            }
            String name = accountLpmireganamtnuoccakvmoca.name;
            Intrinsics.checkNotNullExpressionValue(name, "name");
            String userData = getAccountManager().getUserData(accountLpmireganamtnuoccakvmoca, "uid");
            Intrinsics.checkNotNullExpressionValue(userData, "getUserData(...)");
            UserId userId2 = new UserId(Long.parseLong(userData));
            String userData2 = getAccountManager().getUserData(accountLpmireganamtnuoccakvmoca, "access_token");
            Intrinsics.checkNotNullExpressionValue(userData2, "getUserData(...)");
            String userData3 = getAccountManager().getUserData(accountLpmireganamtnuoccakvmoca, SECRET_ARG);
            String userData4 = getAccountManager().getUserData(accountLpmireganamtnuoccakvmoca, "expires_in");
            int iIntValue = 0;
            int iIntValue2 = (userData4 == null || (intOrNull2 = StringsKt.toIntOrNull(userData4)) == null) ? 0 : intOrNull2.intValue();
            String userData5 = getAccountManager().getUserData(accountLpmireganamtnuoccakvmoca, CREATED_ARG);
            long jLongValue = (userData5 == null || (longOrNull = StringsKt.toLongOrNull(userData5)) == null) ? 0L : longOrNull.longValue();
            String userData6 = getAccountManager().getUserData(accountLpmireganamtnuoccakvmoca, ORDINAL_ARG);
            if (userData6 != null && (intOrNull = StringsKt.toIntOrNull(userData6)) != null) {
                iIntValue = intOrNull.intValue();
            }
            int i10 = iIntValue;
            String userData7 = getAccountManager().getUserData(accountLpmireganamtnuoccakvmoca, "exchange_token");
            if (userData7 == null) {
                userData7 = "";
            }
            return new AccountManagerData(userId2, name, userData2, userData3, iIntValue2, jLongValue, i10, userData7, lpmireganamtnuoccakvmocb(accountLpmireganamtnuoccakvmoca), lpmireganamtnuoccakvmoca(accountLpmireganamtnuoccakvmoca));
        } catch (Exception e10) {
            WebLogger.INSTANCE.e(e10);
            return null;
        }
    }

    @Override // com.vk.accountmanager.domain.AccountManagerRepository
    @NotNull
    public AccountManager getAccountManager() {
        return this.accountManager;
    }

    @Override // com.vk.accountmanager.domain.AccountManagerRepository
    @NotNull
    public String getAccountType() {
        return (String) this.accountType.getValue();
    }

    @Override // com.vk.accountmanager.domain.AccountManagerRepository
    @WorkerThread
    @NotNull
    public synchronized List<AccountManagerData> getAllAccounts() {
        return getAllAccountsUnsafe();
    }

    @Override // com.vk.accountmanager.domain.AccountManagerRepository
    @NotNull
    public List<AccountManagerData> getAllAccountsUnsafe() {
        Integer intOrNull;
        Long longOrNull;
        Integer intOrNull2;
        try {
            Account[] accountsByTypeForPackage = getAccountManager().getAccountsByTypeForPackage(getAccountType(), getLpmireganamtnuoccakvmoca().getPackageName());
            Intrinsics.checkNotNullExpressionValue(accountsByTypeForPackage, "getAccountsByTypeForPackage(...)");
            ArrayList arrayList = new ArrayList();
            for (Account account : accountsByTypeForPackage) {
                String userData = getAccountManager().getUserData(account, "uid");
                if ((userData != null ? StringsKt.toLongOrNull(userData) : null) != null) {
                    arrayList.add(account);
                }
            }
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList, 10));
            int size = arrayList.size();
            int i10 = 0;
            while (i10 < size) {
                Object obj = arrayList.get(i10);
                i10++;
                Account account2 = (Account) obj;
                String name = account2.name;
                Intrinsics.checkNotNullExpressionValue(name, "name");
                String userData2 = getAccountManager().getUserData(account2, "uid");
                Intrinsics.checkNotNullExpressionValue(userData2, "getUserData(...)");
                UserId userId = new UserId(Long.parseLong(userData2));
                String userData3 = getAccountManager().getUserData(account2, "access_token");
                Intrinsics.checkNotNullExpressionValue(userData3, "getUserData(...)");
                String userData4 = getAccountManager().getUserData(account2, SECRET_ARG);
                String userData5 = getAccountManager().getUserData(account2, "expires_in");
                int iIntValue = (userData5 == null || (intOrNull2 = StringsKt.toIntOrNull(userData5)) == null) ? 0 : intOrNull2.intValue();
                String userData6 = getAccountManager().getUserData(account2, CREATED_ARG);
                long jLongValue = (userData6 == null || (longOrNull = StringsKt.toLongOrNull(userData6)) == null) ? 0L : longOrNull.longValue();
                String userData7 = getAccountManager().getUserData(account2, ORDINAL_ARG);
                int iIntValue2 = (userData7 == null || (intOrNull = StringsKt.toIntOrNull(userData7)) == null) ? 0 : intOrNull.intValue();
                String userData8 = getAccountManager().getUserData(account2, "exchange_token");
                if (userData8 == null) {
                    userData8 = "";
                }
                Intrinsics.checkNotNull(account2);
                arrayList2.add(new AccountManagerData(userId, name, userData3, userData4, iIntValue, jLongValue, iIntValue2, userData8, lpmireganamtnuoccakvmocb(account2), lpmireganamtnuoccakvmoca(account2)));
            }
            return arrayList2;
        } catch (Exception e10) {
            WebLogger.INSTANCE.e(e10);
            return CollectionsKt.emptyList();
        }
    }

    @Override // com.vk.accountmanager.domain.AccountManagerRepository
    @WorkerThread
    @Nullable
    public synchronized Account updateData(@NotNull AccountManagerData data) {
        Intrinsics.checkNotNullParameter(data, "data");
        try {
            if (lpmireganamtnuoccakvmoca(data.getUid()) == null) {
                WebLogger.INSTANCE.i("Update data was called when user does not contain");
                return null;
            }
            return addAccountToAccountManager(new AccountManagerData(data.getUid(), data.getUsername(), data.getAccessToken(), data.getSecret(), data.getExpiresInSec(), data.getCreatedMs(), data.getOrdinal(), data.getExchangeToken(), data.getAccountProfileType(), data.getMasterAccountId()));
        } catch (Exception e10) {
            WebLogger.INSTANCE.e(e10);
            return null;
        }
    }

    private final List<Account> lpmireganamtnuoccakvmoca(Account account, Account[] accountArr) {
        long jLongValue;
        UserId userIdLpmireganamtnuoccakvmoca;
        if (account != null) {
            try {
                if (lpmireganamtnuoccakvmocb(account) == AccountProfileType.NORMAL) {
                    String userData = getAccountManager().getUserData(account, "uid");
                    Intrinsics.checkNotNullExpressionValue(userData, "getUserData(...)");
                    Long longOrNull = StringsKt.toLongOrNull(userData);
                    if (longOrNull != null) {
                        jLongValue = longOrNull.longValue();
                    } else {
                        String name = account.name;
                        Intrinsics.checkNotNullExpressionValue(name, "name");
                        Long longOrNull2 = StringsKt.toLongOrNull(name);
                        if (longOrNull2 == null) {
                            return CollectionsKt.emptyList();
                        }
                        jLongValue = longOrNull2.longValue();
                    }
                    ArrayList arrayList = new ArrayList();
                    for (Account account2 : accountArr) {
                        if (lpmireganamtnuoccakvmocb(account2) != AccountProfileType.NORMAL && (userIdLpmireganamtnuoccakvmoca = lpmireganamtnuoccakvmoca(account2)) != null && userIdLpmireganamtnuoccakvmoca.getValue() == jLongValue) {
                            arrayList.add(account2);
                        }
                    }
                    return arrayList;
                }
            } catch (Throwable unused) {
                return CollectionsKt.emptyList();
            }
        }
        return CollectionsKt.emptyList();
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ AccountManagerRepositoryImpl(Context context, SessionStatInteractor sessionStatInteractor, AccountManager accountManager, int i10, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i10 & 4) != 0) {
            accountManager = AccountManager.get(context);
            Intrinsics.checkNotNullExpressionValue(accountManager, "get(...)");
        }
        this(context, sessionStatInteractor, accountManager);
    }

    private final UserId lpmireganamtnuoccakvmoca(Account account) {
        try {
            String userData = getAccountManager().getUserData(account, MASTER_ACCOUNT_ID_ARG);
            Intrinsics.checkNotNullExpressionValue(userData, "getUserData(...)");
            Long longOrNull = StringsKt.toLongOrNull(userData);
            if (longOrNull != null) {
                if (longOrNull.longValue() != UserId.DEFAULT.getValue()) {
                    return new UserId(longOrNull.longValue());
                }
            }
        } catch (Exception unused) {
        }
        return null;
    }

    private final Account lpmireganamtnuoccakvmoca(UserId userId) {
        Long longOrNull;
        Account[] accountsByTypeForPackage = getAccountManager().getAccountsByTypeForPackage(getAccountType(), getLpmireganamtnuoccakvmoca().getPackageName());
        Intrinsics.checkNotNullExpressionValue(accountsByTypeForPackage, "getAccountsByTypeForPackage(...)");
        for (Account account : accountsByTypeForPackage) {
            String userData = getAccountManager().getUserData(account, "uid");
            if (Intrinsics.areEqual(new UserId((userData == null || (longOrNull = StringsKt.toLongOrNull(userData)) == null) ? UserId.DEFAULT.getValue() : longOrNull.longValue()), userId)) {
                return account;
            }
        }
        return null;
    }
}
