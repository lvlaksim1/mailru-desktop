package ru.mail.data.cmd.server.parser;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.logic.content.AttachInformation;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¨\u0006\b"}, d2 = {"Lru/mail/data/cmd/server/parser/ScanStatusParser;", "", "<init>", "()V", "parseScanStatus", "Lru/mail/logic/content/AttachInformation$ScanStatus;", "scanStatus", "", "message-content_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ScanStatusParser {
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @NotNull
    public final AttachInformation.ScanStatus parseScanStatus(@Nullable String scanStatus) {
        if (scanStatus == null || scanStatus.length() == 0) {
            return AttachInformation.ScanStatus.NOT_YET;
        }
        switch (scanStatus.hashCode()) {
            case -868070612:
                if (scanStatus.equals("toobig")) {
                    return AttachInformation.ScanStatus.TOO_BIG;
                }
                break;
            case 96784904:
                if (scanStatus.equals("error")) {
                    return AttachInformation.ScanStatus.ERROR;
                }
                break;
            case 169161588:
                if (scanStatus.equals("infected")) {
                    return AttachInformation.ScanStatus.INFECTED;
                }
                break;
            case 795560349:
                if (scanStatus.equals("healthy")) {
                    return AttachInformation.ScanStatus.HEALTHY;
                }
                break;
        }
        return AttachInformation.ScanStatus.NOT_YET;
    }
}
