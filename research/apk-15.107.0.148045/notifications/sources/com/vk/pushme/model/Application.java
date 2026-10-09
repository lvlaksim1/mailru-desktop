package com.vk.pushme.model;

import androidx.annotation.WorkerThread;
import com.vk.pushme.model.result.SubscriptionResult;
import com.vk.pushme.model.result.UnsubscribeResult;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes19.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u001e\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001:\u0001\u001aJ\u001e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH&J\u001c\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH&J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u0006H&J\u0016\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&J\u000e\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\u0003H&J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0006H'J\u0018\u0010\u0015\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0005\u001a\u00020\u0006H¦@¢\u0006\u0002\u0010\u0016J\u0014\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\b0\u0018H¦@¢\u0006\u0002\u0010\u0019¨\u0006\u001bÀ\u0006\u0003"}, d2 = {"Lcom/vk/pushme/model/Application;", "", "registerAccount", "Lcom/vk/pushme/model/Request;", "Lcom/vk/pushme/model/result/SubscriptionResult;", "account", "", "settings", "Lcom/vk/pushme/model/SubscriptionSettings;", "registerAccounts", "accounts", "", "Lcom/vk/pushme/model/Application$AccountRequest;", "editSubscription", "Lcom/vk/pushme/model/SubscriptionSettingsBuilder;", "unregisterAccount", "Lcom/vk/pushme/model/result/UnsubscribeResult;", "unregisterAllAccounts", "unsubscribeByToken", "", "token", "findSubscription", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllSubscriptions", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "AccountRequest", "push-me-sdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface Application {

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/vk/pushme/model/Application$AccountRequest;", "", "account", "", "settings", "Lcom/vk/pushme/model/SubscriptionSettings;", "<init>", "(Ljava/lang/String;Lcom/vk/pushme/model/SubscriptionSettings;)V", "getAccount", "()Ljava/lang/String;", "getSettings", "()Lcom/vk/pushme/model/SubscriptionSettings;", "push-me-sdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class AccountRequest {

        @NotNull
        private final String account;

        @NotNull
        private final SubscriptionSettings settings;

        public AccountRequest(@NotNull String account, @NotNull SubscriptionSettings settings) {
            Intrinsics.checkNotNullParameter(account, "account");
            Intrinsics.checkNotNullParameter(settings, "settings");
            this.account = account;
            this.settings = settings;
        }

        @NotNull
        public final String getAccount() {
            return this.account;
        }

        @NotNull
        public final SubscriptionSettings getSettings() {
            return this.settings;
        }
    }

    @NotNull
    SubscriptionSettingsBuilder editSubscription(@NotNull String account);

    @Nullable
    Object findSubscription(@NotNull String str, @NotNull Continuation<? super SubscriptionSettings> continuation);

    @Nullable
    Object getAllSubscriptions(@NotNull Continuation<? super Collection<SubscriptionSettings>> continuation);

    @NotNull
    Request<SubscriptionResult> registerAccount(@NotNull String account, @NotNull SubscriptionSettings settings);

    @NotNull
    Request<SubscriptionResult> registerAccounts(@NotNull List<AccountRequest> accounts);

    @NotNull
    Request<UnsubscribeResult> unregisterAccount(@NotNull String account);

    @NotNull
    Request<UnsubscribeResult> unregisterAllAccounts();

    @WorkerThread
    void unsubscribeByToken(@NotNull String token);
}
