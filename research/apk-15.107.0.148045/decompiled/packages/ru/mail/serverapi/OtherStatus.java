package ru.mail.serverapi;

import java.util.ArrayList;
import ru.mail.network.CommandStatusMessageGenerator;
import ru.mail.network.NetworkCommandStatus;
import ru.mail.utils.ArrayUtils;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
public class OtherStatus extends CommandStatusMessageGenerator.ClassEquals {
    private static final String MAIL_SPECIFIC_ERROR = "MAIL_SPECIFIC_ERROR";

    public OtherStatus() {
        super(toHolders(getAllMailStatuses()));
    }

    private static Class<?>[] getAllMailStatuses() {
        return (Class[]) ArrayUtils.concatArrays(MailCommandStatus.class.getClasses(), NetworkCommandStatus.FOLDER_ACCESS_DENIED.class);
    }

    private static CommandStatusMessageGenerator.ClassEquals.ClassAndMeta[] toHolders(Class<?>[] clsArr) {
        ArrayList arrayList = new ArrayList();
        for (Class<?> cls : clsArr) {
            arrayList.add(new CommandStatusMessageGenerator.ClassEquals.ClassAndMeta(cls, MAIL_SPECIFIC_ERROR));
        }
        return (CommandStatusMessageGenerator.ClassEquals.ClassAndMeta[]) arrayList.toArray(new CommandStatusMessageGenerator.ClassEquals.ClassAndMeta[arrayList.size()]);
    }
}
