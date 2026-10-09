package ru.mail.rustoresdk;

import androidx.annotation.WorkerThread;
import com.vk.api.sdk.exceptions.VKApiCodes;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import ru.mail.rustoresdk.message.VkpnsRemoteMessage;
import ru.ok.android.api.methods.batch.execute.BatchApiRequest;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH'J\b\u0010\t\u001a\u00020\u0003H&J\u0016\u0010\n\u001a\u00020\u00032\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH&¨\u0006\u000eÀ\u0006\u0003"}, d2 = {"Lru/mail/rustoresdk/VkpnsEventsListener;", "", "onNewToken", "", "token", "", "onMessageReceived", "message", "Lru/mail/rustoresdk/message/VkpnsRemoteMessage;", "onDeletedMessages", BatchApiRequest.FIELD_NAME_ON_ERROR, VKApiCodes.PARAM_ERROR_MULTI, "", "Lru/mail/rustoresdk/VkpnsException;", "rustore-sdk-api_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface VkpnsEventsListener {
    void onDeletedMessages();

    void onError(@NotNull List<? extends VkpnsException> errors);

    @WorkerThread
    void onMessageReceived(@NotNull VkpnsRemoteMessage message);

    @WorkerThread
    void onNewToken(@NotNull String token);
}
