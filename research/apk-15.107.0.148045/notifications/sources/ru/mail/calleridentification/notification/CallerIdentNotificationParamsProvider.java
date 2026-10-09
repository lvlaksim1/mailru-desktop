package ru.mail.calleridentification.notification;

import android.content.Context;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import ru.mail.calleridentification.CallerIdentNotificationParams;
import ru.mail.calleridentification.R;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\n¨\u0006\r"}, d2 = {"Lru/mail/calleridentification/notification/CallerIdentNotificationParamsProvider;", "Lru/mail/calleridentification/CallerIdentNotificationParams;", "context", "Landroid/content/Context;", "callerTitle", "", "<init>", "(Landroid/content/Context;Ljava/lang/String;)V", "title", "getTitle", "()Ljava/lang/String;", "text", "getText", "caller-identification-impl_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class CallerIdentNotificationParamsProvider implements CallerIdentNotificationParams {

    @NotNull
    private final String text;

    @NotNull
    private final String title;

    public CallerIdentNotificationParamsProvider(@NotNull Context context, @NotNull String callerTitle) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(callerTitle, "callerTitle");
        String string = context.getString(R.string.caller_ntf_title);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        this.title = string;
        String string2 = context.getString(R.string.caller_ntf_text, callerTitle);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
        this.text = string2;
    }

    @Override // ru.mail.calleridentification.CallerIdentNotificationParams
    @NotNull
    public String getText() {
        return this.text;
    }

    @Override // ru.mail.calleridentification.CallerIdentNotificationParams
    @NotNull
    public String getTitle() {
        return this.title;
    }
}
