package com.vk.pushme.network;

import com.vk.pushme.network.model.request.SubscriptionRequest;
import com.vk.pushme.network.model.result.SubscriptionResult;
import com.vk.pushme.network.model.result.UnsubscribeResult;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.data.cmd.server.ad.RbParams;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\bf\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H¦@¢\u0006\u0002\u0010\u0007J\u001c\u0010\b\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H¦@¢\u0006\u0002\u0010\u0007J(\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\fH¦@¢\u0006\u0002\u0010\u000fJ\u001e\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\fH¦@¢\u0006\u0002\u0010\u0012¨\u0006\u0013À\u0006\u0003"}, d2 = {"Lcom/vk/pushme/network/PushMeApi;", "", "setSettingsV1", "Lcom/vk/pushme/network/model/result/SubscriptionResult;", "subscriptions", "", "Lcom/vk/pushme/network/model/request/SubscriptionRequest;", "(Ljava/util/Collection;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setSettingsV2", "unsubscribeByDeviceId", "Lcom/vk/pushme/network/model/result/UnsubscribeResult;", "account", "", RbParams.Default.URL_PARAM_KEY_DEVICE_ID, "application", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "unsubscribeByToken", "token", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "push-me-network_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface PushMeApi {
    @Nullable
    Object setSettingsV1(@NotNull Collection<SubscriptionRequest> collection, @NotNull Continuation<? super SubscriptionResult> continuation);

    @Nullable
    Object setSettingsV2(@NotNull Collection<SubscriptionRequest> collection, @NotNull Continuation<? super SubscriptionResult> continuation);

    @Nullable
    Object unsubscribeByDeviceId(@NotNull String str, @NotNull String str2, @Nullable String str3, @NotNull Continuation<? super UnsubscribeResult> continuation);

    @Nullable
    Object unsubscribeByToken(@NotNull String str, @NotNull String str2, @NotNull Continuation<? super UnsubscribeResult> continuation);
}
