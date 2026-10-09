package com.vk.pushme.network;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes19.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u001e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0006H¦@¢\u0006\u0004\b\u0007\u0010\b¨\u0006\tÀ\u0006\u0003"}, d2 = {"Lcom/vk/pushme/network/AnalyticsApi;", "", "sendRequest", "Lkotlin/Result;", "", "callbackUrl", "", "sendRequest-gIAlu-s", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "push-me-network_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface AnalyticsApi {
    @Nullable
    /* JADX INFO: renamed from: sendRequest-gIAlu-s, reason: not valid java name */
    Object mo12678sendRequestgIAlus(@NotNull String str, @NotNull Continuation<? super Result<Unit>> continuation);
}
