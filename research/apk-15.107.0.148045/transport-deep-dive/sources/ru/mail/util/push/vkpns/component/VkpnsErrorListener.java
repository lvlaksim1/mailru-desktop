package ru.mail.util.push.vkpns.component;

import com.vk.api.sdk.exceptions.VKApiCodes;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import ru.mail.rustoresdk.VkpnsException;
import ru.ok.android.api.methods.batch.execute.BatchApiRequest;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&¨\u0006\u0007À\u0006\u0003"}, d2 = {"Lru/mail/util/push/vkpns/component/VkpnsErrorListener;", "", BatchApiRequest.FIELD_NAME_ON_ERROR, "", VKApiCodes.PARAM_ERROR_MULTI, "", "Lru/mail/rustoresdk/VkpnsException;", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface VkpnsErrorListener {
    void onError(@NotNull List<? extends VkpnsException> errors);
}
