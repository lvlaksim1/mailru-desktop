package ru.mail.data.cmd.server;

import android.content.Context;
import androidx.compose.runtime.internal.StabilityInferred;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import ru.mail.mails.R;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000e\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0016J\b\u0010\t\u001a\u00020\u0007H\u0016J\b\u0010\n\u001a\u00020\u0007H\u0016J\u0010\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0007H\u0016J\u0010\u0010\r\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u0007H\u0016J\u0010\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u0007H\u0016J\b\u0010\u0011\u001a\u00020\u0007H\u0016J\b\u0010\u0012\u001a\u00020\u0007H\u0016J\b\u0010\u0013\u001a\u00020\u0007H\u0016J\b\u0010\u0014\u001a\u00020\u0007H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lru/mail/data/cmd/server/ErrorStringProviderImpl;", "Lru/mail/data/cmd/server/ErrorStringProvider;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "getAttachmentsTooBigQuota", "", "sizeInMb", "getAttachmentsDisable", "getWrongEmail", "getAttachToLarge", "file", "getInvalidRecipient", "emailWithError", "getQuotasEmailLimitExceeded", "email", "getSendDateError", "getSendLimitExceeded", "getAttachWasNotFound", "getFileExistError", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ErrorStringProviderImpl implements ErrorStringProvider {
    public static final int $stable = 8;

    @NotNull
    private final Context context;

    public ErrorStringProviderImpl(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    @Override // ru.mail.data.cmd.server.ErrorStringProvider
    @NotNull
    public String getAttachToLarge(@NotNull String file) {
        Intrinsics.checkNotNullParameter(file, "file");
        String string = this.context.getString(R.string.attach_too_large, file);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return string;
    }

    @Override // ru.mail.data.cmd.server.ErrorStringProvider
    @NotNull
    public String getAttachWasNotFound() {
        String string = this.context.getString(R.string.attach_was_not_found);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return string;
    }

    @Override // ru.mail.data.cmd.server.ErrorStringProvider
    @NotNull
    public String getAttachmentsDisable() {
        String string = this.context.getString(R.string.attachments_disable);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return string;
    }

    @Override // ru.mail.data.cmd.server.ErrorStringProvider
    @NotNull
    public String getAttachmentsTooBigQuota(@NotNull String sizeInMb) {
        Intrinsics.checkNotNullParameter(sizeInMb, "sizeInMb");
        String string = this.context.getString(R.string.attachments_too_big_quota, sizeInMb);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return string;
    }

    @Override // ru.mail.data.cmd.server.ErrorStringProvider
    @NotNull
    public String getFileExistError() {
        String string = this.context.getString(R.string.attachments_exist);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return string;
    }

    @Override // ru.mail.data.cmd.server.ErrorStringProvider
    @NotNull
    public String getInvalidRecipient(@NotNull String emailWithError) {
        Intrinsics.checkNotNullParameter(emailWithError, "emailWithError");
        String string = this.context.getString(R.string.invalid_recipient, emailWithError);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return string;
    }

    @Override // ru.mail.data.cmd.server.ErrorStringProvider
    @NotNull
    public String getQuotasEmailLimitExceeded(@NotNull String email) {
        Intrinsics.checkNotNullParameter(email, "email");
        String string = this.context.getString(R.string.quotas_email_limit_exceeded, email);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return string;
    }

    @Override // ru.mail.data.cmd.server.ErrorStringProvider
    @NotNull
    public String getSendDateError() {
        String string = this.context.getString(R.string.send_date_error);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return string;
    }

    @Override // ru.mail.data.cmd.server.ErrorStringProvider
    @NotNull
    public String getSendLimitExceeded() {
        String string = this.context.getString(R.string.quotas_send_limit_exceeded);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return string;
    }

    @Override // ru.mail.data.cmd.server.ErrorStringProvider
    @NotNull
    public String getWrongEmail() {
        String string = this.context.getString(R.string.wrong_email);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return string;
    }
}
