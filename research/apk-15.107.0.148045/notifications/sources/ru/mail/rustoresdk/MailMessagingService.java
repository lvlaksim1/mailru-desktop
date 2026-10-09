package ru.mail.rustoresdk;

import com.vk.api.sdk.exceptions.VKApiCodes;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import ru.mail.rustoresdk.MailMessagingService;
import ru.mail.rustoresdk.message.VkpnsRemoteMessage;
import ru.mail.util.log.Log;
import ru.ok.android.api.methods.batch.execute.BatchApiRequest;
import ru.rustore.sdk.pushclient.messaging.exception.RuStorePushClientException;
import ru.rustore.sdk.pushclient.messaging.model.RemoteMessage;
import ru.rustore.sdk.pushclient.messaging.service.RuStoreMessagingService;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\u0005H\u0016J\u0016\u0010\f\u001a\u00020\u00052\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u0016¨\u0006\u0011"}, d2 = {"Lru/mail/rustoresdk/MailMessagingService;", "Lru/rustore/sdk/pushclient/messaging/service/RuStoreMessagingService;", "<init>", "()V", "onNewToken", "", "token", "", "onMessageReceived", "message", "Lru/rustore/sdk/pushclient/messaging/model/RemoteMessage;", "onDeletedMessages", BatchApiRequest.FIELD_NAME_ON_ERROR, VKApiCodes.PARAM_ERROR_MULTI, "", "Lru/rustore/sdk/pushclient/messaging/exception/RuStorePushClientException;", "Companion", "rustore-sdk-impl_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nMailMessagingService.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MailMessagingService.kt\nru/mail/rustoresdk/MailMessagingService\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,34:1\n1563#2:35\n1634#2,3:36\n*S KotlinDebug\n*F\n+ 1 MailMessagingService.kt\nru/mail/rustoresdk/MailMessagingService\n*L\n28#1:35\n28#1:36,3\n*E\n"})
public final class MailMessagingService extends RuStoreMessagingService {

    @NotNull
    private static final Log LOG = Log.INSTANCE.getLog("MailMessagingService");

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence onError$lambda$0(RuStorePushClientException it) {
        Intrinsics.checkNotNullParameter(it, "it");
        String simpleName = it.getClass().getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "getSimpleName(...)");
        return simpleName;
    }

    @Override // ru.rustore.sdk.pushclient.messaging.service.RuStoreMessagingService
    public void onDeletedMessages() {
        LOG.i("Called onDeletedMessages");
        VkpnsEventsListener listener = VkpnsEventsListenerHolderImpl.INSTANCE.getListener();
        if (listener != null) {
            listener.onDeletedMessages();
        }
    }

    @Override // ru.rustore.sdk.pushclient.messaging.service.RuStoreMessagingService
    public void onError(@NotNull List<? extends RuStorePushClientException> errors) {
        Intrinsics.checkNotNullParameter(errors, "errors");
        List<? extends RuStorePushClientException> list = errors;
        LOG.i("Called onError, errors: " + CollectionsKt.joinToString$default(list, null, null, null, 0, null, new Function1() { // from class: bf.a
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MailMessagingService.onError$lambda$0((RuStorePushClientException) obj);
            }
        }, 31, null));
        VkpnsEventsListener listener = VkpnsEventsListenerHolderImpl.INSTANCE.getListener();
        if (listener != null) {
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(RuStoreEntitiesMapper.INSTANCE.map((RuStorePushClientException) it.next()));
            }
            listener.onError(arrayList);
        }
    }

    @Override // ru.rustore.sdk.pushclient.messaging.service.RuStoreMessagingService
    public void onMessageReceived(@NotNull RemoteMessage message) {
        Intrinsics.checkNotNullParameter(message, "message");
        LOG.i("Called onMessageReceived");
        VkpnsRemoteMessage map = RuStoreEntitiesMapper.INSTANCE.map(message);
        VkpnsEventsListener listener = VkpnsEventsListenerHolderImpl.INSTANCE.getListener();
        if (listener != null) {
            listener.onMessageReceived(map);
        }
    }

    @Override // ru.rustore.sdk.pushclient.messaging.service.RuStoreMessagingService
    public void onNewToken(@NotNull String token) {
        Intrinsics.checkNotNullParameter(token, "token");
        LOG.i("Called onNewToken");
        VkpnsEventsListener listener = VkpnsEventsListenerHolderImpl.INSTANCE.getListener();
        if (listener != null) {
            listener.onNewToken(token);
        }
    }
}
