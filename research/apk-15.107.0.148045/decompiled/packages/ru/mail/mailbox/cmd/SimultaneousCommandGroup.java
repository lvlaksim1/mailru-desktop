package ru.mail.mailbox.cmd;

import androidx.annotation.NonNull;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes10.dex */
public class SimultaneousCommandGroup extends BaseSimultaneousCommandGroup<Map<Command<?, ?>, Object>> {
    public SimultaneousCommandGroup() {
    }

    @Override // ru.mail.mailbox.cmd.BaseSimultaneousCommandGroup
    protected /* bridge */ /* synthetic */ Map<Command<?, ?>, Object> convertToResult(Map map) {
        return convertToResult2((Map<Command<?, ?>, BaseSimultaneousCommandGroup.ResultHolder>) map);
    }

    public SimultaneousCommandGroup(Command<?, ?>... commandArr) {
        super(commandArr);
    }

    @Override // ru.mail.mailbox.cmd.BaseSimultaneousCommandGroup
    /* JADX INFO: renamed from: convertToResult, reason: avoid collision after fix types in other method */
    protected Map<Command<?, ?>, Object> convertToResult2(Map<Command<?, ?>, BaseSimultaneousCommandGroup.ResultHolder> map) {
        HashMap map2 = new HashMap();
        for (Map.Entry<Command<?, ?>, BaseSimultaneousCommandGroup.ResultHolder> entry : map.entrySet()) {
            map2.put(entry.getKey(), entry.getValue().getResult());
        }
        return map2;
    }

    @Override // ru.mail.mailbox.cmd.Command
    @NonNull
    public synchronized Map<Command<?, ?>, Object> getResult() {
        Map map;
        try {
            map = (Map) super.getResult();
        } catch (Throwable th2) {
            throw th2;
        }
        return map == null ? Collections.EMPTY_MAP : Collections.unmodifiableMap(map);
    }
}
