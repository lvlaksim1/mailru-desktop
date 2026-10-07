package ru.mail.auth.loginactivity;

import android.os.Bundle;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.auth.Authenticator;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityDataState;", "", "<init>", "()V", "LudwigCaptcha", "Common", "Lru/mail/auth/loginactivity/LoginActivityDataState$Common;", "Lru/mail/auth/loginactivity/LoginActivityDataState$LudwigCaptcha;", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class LoginActivityDataState {

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0013\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityDataState$Common;", "Lru/mail/auth/loginactivity/LoginActivityDataState;", "bundle", "Landroid/os/Bundle;", "<init>", "(Landroid/os/Bundle;)V", "getBundle", "()Landroid/os/Bundle;", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Common extends LoginActivityDataState {

        @Nullable
        private final Bundle bundle;

        /* JADX WARN: Multi-variable type inference failed */
        public Common() {
            this(null, 1, 0 == true ? 1 : 0);
        }

        @Nullable
        public final Bundle getBundle() {
            return this.bundle;
        }

        public Common(@Nullable Bundle bundle) {
            super(null);
            this.bundle = bundle;
        }

        public /* synthetic */ Common(Bundle bundle, int i10, DefaultConstructorMarker defaultConstructorMarker) {
            this((i10 & 1) != 0 ? null : bundle);
        }
    }

    public /* synthetic */ LoginActivityDataState(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private LoginActivityDataState() {
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u0001B;\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0014"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityDataState$LudwigCaptcha;", "Lru/mail/auth/loginactivity/LoginActivityDataState;", "ludwigToken", "", "login", "password", "type", "Lru/mail/auth/Authenticator$Type;", "bundle", "Landroid/os/Bundle;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lru/mail/auth/Authenticator$Type;Landroid/os/Bundle;)V", "getLudwigToken", "()Ljava/lang/String;", "getLogin", "getPassword", "getType", "()Lru/mail/auth/Authenticator$Type;", "getBundle", "()Landroid/os/Bundle;", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class LudwigCaptcha extends LoginActivityDataState {

        @Nullable
        private final Bundle bundle;

        @Nullable
        private final String login;

        @NotNull
        private final String ludwigToken;

        @Nullable
        private final String password;

        @NotNull
        private final Authenticator.Type type;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public LudwigCaptcha(@NotNull String ludwigToken, @Nullable String str, @Nullable String str2, @NotNull Authenticator.Type type, @Nullable Bundle bundle) {
            super(null);
            Intrinsics.checkNotNullParameter(ludwigToken, "ludwigToken");
            Intrinsics.checkNotNullParameter(type, "type");
            this.ludwigToken = ludwigToken;
            this.login = str;
            this.password = str2;
            this.type = type;
            this.bundle = bundle;
        }

        @Nullable
        public final Bundle getBundle() {
            return this.bundle;
        }

        @Nullable
        public final String getLogin() {
            return this.login;
        }

        @NotNull
        public final String getLudwigToken() {
            return this.ludwigToken;
        }

        @Nullable
        public final String getPassword() {
            return this.password;
        }

        @NotNull
        public final Authenticator.Type getType() {
            return this.type;
        }

        public /* synthetic */ LudwigCaptcha(String str, String str2, String str3, Authenticator.Type type, Bundle bundle, int i10, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (i10 & 2) != 0 ? null : str2, (i10 & 4) != 0 ? null : str3, type, (i10 & 16) != 0 ? null : bundle);
        }
    }
}
