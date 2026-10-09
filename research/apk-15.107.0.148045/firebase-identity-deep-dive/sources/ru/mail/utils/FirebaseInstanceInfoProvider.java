package ru.mail.utils;

import android.content.Context;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.util.log.Log;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\u0012\u0010\b\u001a\u0004\u0018\u00010\u00072\u0006\u0010\t\u001a\u00020\u0007H\u0016J\b\u0010\n\u001a\u00020\u000bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lru/mail/utils/FirebaseInstanceInfoProvider;", "Lru/mail/utils/FirebaseInfoProvider;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "getInstanceId", "", "getToken", "senderId", "deleteToken", "", "Companion", "mail-utils_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class FirebaseInstanceInfoProvider implements FirebaseInfoProvider {

    @NotNull
    private static final Companion Companion = new Companion(null);

    @NotNull
    private static final Log LOG = Log.INSTANCE.getLog("FirebaseInfoProvider");

    @NotNull
    private final Context context;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lru/mail/utils/FirebaseInstanceInfoProvider$Companion;", "", "<init>", "()V", "LOG", "Lru/mail/util/log/Log;", "mail-utils_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public FirebaseInstanceInfoProvider(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    @Override // ru.mail.utils.FirebaseInfoProvider
    public void deleteToken() throws IOException {
        FirebaseAppInitializer.INSTANCE.awaitInitialized(this.context);
        FirebaseInstanceId.getInstance().deleteInstanceId();
    }

    @Override // ru.mail.utils.FirebaseInfoProvider
    @NotNull
    public String getInstanceId() {
        Object objM13123constructorimpl;
        try {
            Result.Companion companion = Result.INSTANCE;
            FirebaseAppInitializer.INSTANCE.awaitInitialized(this.context);
            objM13123constructorimpl = Result.m13123constructorimpl(FirebaseInstanceId.getInstance().getId());
        } catch (Throwable th2) {
            Result.Companion companion2 = Result.INSTANCE;
            objM13123constructorimpl = Result.m13123constructorimpl(ResultKt.createFailure(th2));
        }
        Throwable thM13126exceptionOrNullimpl = Result.m13126exceptionOrNullimpl(objM13123constructorimpl);
        if (thM13126exceptionOrNullimpl != null) {
            LOG.e("Failed to get firebase instance id", thM13126exceptionOrNullimpl);
            objM13123constructorimpl = "";
        }
        return (String) objM13123constructorimpl;
    }

    @Override // ru.mail.utils.FirebaseInfoProvider
    @Nullable
    public String getToken(@NotNull String senderId) {
        Intrinsics.checkNotNullParameter(senderId, "senderId");
        FirebaseAppInitializer.INSTANCE.awaitInitialized(this.context);
        return FirebaseInstanceId.getInstance().getToken(senderId, FirebaseMessaging.INSTANCE_ID_SCOPE);
    }
}
