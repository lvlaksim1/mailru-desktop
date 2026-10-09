package ru.mail.util.push.pusher.params;

import java.util.Collection;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J#\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H&¢\u0006\u0004\b\b\u0010\t¨\u0006\nÀ\u0006\u0003"}, d2 = {"Lru/mail/util/push/pusher/params/PushParamsPreparer;", "", "preparePushSettings", "Lkotlin/Result;", "Lorg/json/JSONArray;", "params", "", "Lru/mail/util/push/pusher/params/SubscribeParams;", "preparePushSettings-IoAF18A", "(Ljava/util/Collection;)Ljava/lang/Object;", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface PushParamsPreparer {
    @NotNull
    /* JADX INFO: renamed from: preparePushSettings-IoAF18A */
    Object mo15887preparePushSettingsIoAF18A(@NotNull Collection<SubscribeParams> params);
}
