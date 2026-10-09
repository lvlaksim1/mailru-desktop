package ru.mail.rustoresdk;

import com.vk.push.pushsdk.utils.JsonMessageParser;
import java.util.Map;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005H&¨\u0006\u0007À\u0006\u0003"}, d2 = {"Lru/mail/rustoresdk/RuStoreSdkTestManager;", "", "sendPush", "", JsonMessageParser.Keys.PAYLOAD_JSON_KEY, "", "", "rustore-sdk-api_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface RuStoreSdkTestManager {
    void sendPush(@NotNull Map<String, String> payload);
}
