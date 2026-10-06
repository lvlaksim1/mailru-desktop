package ru.mail.data.cmd.server;

import ru.mail.serverapi.ServerCommandBaseParams;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
public class EmptyServerCommandBaseParams extends ServerCommandBaseParams {
    private static EmptyServerCommandBaseParams sEmptyParams;
    private final Object mHashCodeCalculator = new Object();

    private EmptyServerCommandBaseParams() {
    }

    public static EmptyServerCommandBaseParams getParams() {
        if (sEmptyParams == null) {
            sEmptyParams = new EmptyServerCommandBaseParams();
        }
        return sEmptyParams;
    }

    @Override // ru.mail.serverapi.ServerCommandBaseParams
    public boolean equals(Object obj) {
        return obj != null && (obj instanceof EmptyServerCommandBaseParams);
    }

    @Override // ru.mail.serverapi.ServerCommandBaseParams
    public int hashCode() {
        return this.mHashCodeCalculator.hashCode();
    }
}
