package ru.mail.util.push;

import android.content.Context;
import java.util.Collection;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
public interface LocalPushPollingStrategy {
    Collection<Long> getCheckedFolders(Context context, String str);
}
