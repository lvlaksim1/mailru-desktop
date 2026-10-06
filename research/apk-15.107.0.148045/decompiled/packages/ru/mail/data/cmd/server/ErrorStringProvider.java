package ru.mail.data.cmd.server;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000e\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H&J\b\u0010\u0005\u001a\u00020\u0003H&J\b\u0010\u0006\u001a\u00020\u0003H&J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u0003H&J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u0003H&J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u0003H&J\b\u0010\r\u001a\u00020\u0003H&J\b\u0010\u000e\u001a\u00020\u0003H&J\b\u0010\u000f\u001a\u00020\u0003H&J\b\u0010\u0010\u001a\u00020\u0003H&¨\u0006\u0011À\u0006\u0003"}, d2 = {"Lru/mail/data/cmd/server/ErrorStringProvider;", "", "getAttachmentsTooBigQuota", "", "sizeInMb", "getAttachmentsDisable", "getWrongEmail", "getAttachToLarge", "file", "getInvalidRecipient", "emailWithError", "getQuotasEmailLimitExceeded", "email", "getSendDateError", "getSendLimitExceeded", "getAttachWasNotFound", "getFileExistError", "message-send_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface ErrorStringProvider {
    @NotNull
    String getAttachToLarge(@NotNull String file);

    @NotNull
    String getAttachWasNotFound();

    @NotNull
    String getAttachmentsDisable();

    @NotNull
    String getAttachmentsTooBigQuota(@NotNull String sizeInMb);

    @NotNull
    String getFileExistError();

    @NotNull
    String getInvalidRecipient(@NotNull String emailWithError);

    @NotNull
    String getQuotasEmailLimitExceeded(@NotNull String email);

    @NotNull
    String getSendDateError();

    @NotNull
    String getSendLimitExceeded();

    @NotNull
    String getWrongEmail();
}
