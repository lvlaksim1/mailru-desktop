package ru.mail.serverapi;

import android.content.Context;
import java.util.Arrays;
import ru.mail.mailbox.cmd.Command;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
public class DependentStatusCmd extends BaseDependentStatusCmd {
    private final Class<?>[] mDependentClasses;

    public DependentStatusCmd(Context context, Class<?> cls, String str, FolderState folderState) {
        this(context, false, cls, str, folderState);
    }

    private boolean isAssignableFrom(Class<?> cls) {
        for (Class<?> cls2 : this.mDependentClasses) {
            if (cls2.isAssignableFrom(cls)) {
                return true;
            }
        }
        return false;
    }

    @Override // ru.mail.serverapi.BaseDependentStatusCmd
    protected boolean isDependentCommand(Command<?, ?> command) {
        return isAssignableFrom(command.getClass());
    }

    public DependentStatusCmd(Context context, boolean z10, Class<?> cls, String str, FolderState folderState) {
        this(context, z10, (Class<?>[]) new Class[]{cls}, str, folderState);
    }

    public DependentStatusCmd(Context context, Class<?>[] clsArr, String str, FolderState folderState) {
        this(context, false, clsArr, str, folderState);
    }

    public DependentStatusCmd(Context context, boolean z10, Class<?>[] clsArr, String str, FolderState folderState) {
        super(context, z10, str, folderState);
        this.mDependentClasses = (Class[]) Arrays.copyOf(clsArr, clsArr.length);
    }
}
