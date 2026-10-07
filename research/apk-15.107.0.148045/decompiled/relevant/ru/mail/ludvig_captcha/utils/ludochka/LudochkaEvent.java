package ru.mail.ludvig_captcha.utils.ludochka;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.http.protocol.HTTP;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b0\u0018\u00002\u00020\u0001:\u0005\u0004\u0005\u0006\u0007\bB\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0005\t\n\u000b\f\r¨\u0006\u000e"}, d2 = {"Lru/mail/ludvig_captcha/utils/ludochka/LudochkaEvent;", "", "<init>", "()V", "Success", "Error", "Resize", HTTP.CONN_CLOSE, "Cancel", "Lru/mail/ludvig_captcha/utils/ludochka/LudochkaEvent$Cancel;", "Lru/mail/ludvig_captcha/utils/ludochka/LudochkaEvent$Close;", "Lru/mail/ludvig_captcha/utils/ludochka/LudochkaEvent$Error;", "Lru/mail/ludvig_captcha/utils/ludochka/LudochkaEvent$Resize;", "Lru/mail/ludvig_captcha/utils/ludochka/LudochkaEvent$Success;", "ludvig-captcha_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class LudochkaEvent {

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lru/mail/ludvig_captcha/utils/ludochka/LudochkaEvent$Cancel;", "Lru/mail/ludvig_captcha/utils/ludochka/LudochkaEvent;", "<init>", "()V", "ludvig-captcha_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Cancel extends LudochkaEvent {

        @NotNull
        public static final Cancel INSTANCE = new Cancel();

        private Cancel() {
            super(null);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lru/mail/ludvig_captcha/utils/ludochka/LudochkaEvent$Close;", "Lru/mail/ludvig_captcha/utils/ludochka/LudochkaEvent;", "<init>", "()V", "ludvig-captcha_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Close extends LudochkaEvent {

        @NotNull
        public static final Close INSTANCE = new Close();

        private Close() {
            super(null);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u0011\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\n"}, d2 = {"Lru/mail/ludvig_captcha/utils/ludochka/LudochkaEvent$Error;", "Lru/mail/ludvig_captcha/utils/ludochka/LudochkaEvent;", "errorRaw", "", "<init>", "(Ljava/lang/String;)V", "error", "getError", "()Ljava/lang/String;", "Companion", "ludvig-captcha_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Error extends LudochkaEvent {

        @NotNull
        public static final String STATUS_FIELD = "status";

        @NotNull
        public static final String UNKNOWN_ERROR = "undefined";

        @NotNull
        private final String error;

        /* JADX WARN: Code duplicated, block: B:11:0x0020  */
        public Error(@Nullable String str) {
            String strValueOf;
            super(null);
            if (str == null || str.length() == 0) {
                strValueOf = "undefined";
            } else {
                try {
                    int iOptInt = new JSONObject(str).optInt("status");
                    if (iOptInt != 0) {
                        strValueOf = String.valueOf(iOptInt);
                    } else {
                        strValueOf = "undefined";
                    }
                } catch (Exception unused) {
                }
            }
            this.error = strValueOf;
        }

        @NotNull
        public final String getError() {
            return this.error;
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lru/mail/ludvig_captcha/utils/ludochka/LudochkaEvent$Resize;", "Lru/mail/ludvig_captcha/utils/ludochka/LudochkaEvent;", "<init>", "()V", "ludvig-captcha_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Resize extends LudochkaEvent {

        @NotNull
        public static final Resize INSTANCE = new Resize();

        private Resize() {
            super(null);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lru/mail/ludvig_captcha/utils/ludochka/LudochkaEvent$Success;", "Lru/mail/ludvig_captcha/utils/ludochka/LudochkaEvent;", "token", "", "<init>", "(Ljava/lang/String;)V", "getToken", "()Ljava/lang/String;", "ludvig-captcha_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Success extends LudochkaEvent {

        @NotNull
        private final String token;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Success(@NotNull String token) {
            super(null);
            Intrinsics.checkNotNullParameter(token, "token");
            this.token = token;
        }

        @NotNull
        public final String getToken() {
            return this.token;
        }
    }

    public /* synthetic */ LudochkaEvent(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private LudochkaEvent() {
    }
}
