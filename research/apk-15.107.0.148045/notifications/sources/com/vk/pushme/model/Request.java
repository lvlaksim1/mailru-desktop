package com.vk.pushme.model;

import com.google.android.gms.ads.RequestConfiguration;
import kotlin.Metadata;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes19.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\b&\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\r\u0010\u0005\u001a\u00028\u0000H&¢\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH&¨\u0006\t"}, d2 = {"Lcom/vk/pushme/model/Request;", RequestConfiguration.MAX_AD_CONTENT_RATING_T, "", "<init>", "()V", "execute", "()Ljava/lang/Object;", "enqueue", "", "push-me-sdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class Request<T> {
    public abstract void enqueue();

    public abstract T execute();
}
