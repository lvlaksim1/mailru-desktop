package ru.mail.util.push;

import android.content.Context;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import ru.mail.mailbox.cmd.ObservableFuture;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
public class ProcessPushMessageInServiceListener implements PushMessagesTransport.PushMessagesEventHandler {
    private final PushMessageVisitor mVisitor;

    public ProcessPushMessageInServiceListener(Context context) {
        this.mVisitor = new PushMessageServiceVisitor(context);
    }

    @Override // ru.mail.util.push.PushMessagesTransport.PushMessagesEventHandler
    public List<ObservableFuture<Void>> handlePushMessagesReceived(List<PushMessage> list) {
        ArrayList arrayList = new ArrayList();
        Iterator<PushMessage> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().accept(this.mVisitor));
        }
        return Collections.unmodifiableList(arrayList);
    }
}
