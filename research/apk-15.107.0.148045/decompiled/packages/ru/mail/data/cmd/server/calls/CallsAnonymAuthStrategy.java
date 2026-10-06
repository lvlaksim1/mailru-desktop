package ru.mail.data.cmd.server.calls;

import androidx.compose.runtime.internal.StabilityInferred;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.apache.http.cookie.ClientCookie;
import org.apache.http.cookie.SM;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.calleridentification.CallsAuthProvider;
import ru.mail.calleridentification.CallsAuthStrategy;
import ru.mail.logic.share.MailToMyselfParameters;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00102\u00020\u0001:\u0002\u000f\u0010B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\b\u0010\b\u001a\u00020\u0007H\u0016J\u001e\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b0\n2\b\u0010\f\u001a\u0004\u0018\u00010\u000bH\u0016J\b\u0010\r\u001a\u00020\u000eH\u0016R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lru/mail/data/cmd/server/calls/CallsAnonymAuthStrategy;", "Lru/mail/calleridentification/CallsAuthStrategy;", "<init>", "()V", "authStore", "Lru/mail/data/cmd/server/calls/CallsAnonymAuthStrategy$AnonAuthStore;", "initialize", "", "discardAuthorization", "getRequestAuthHeaders", "", "", "authToken", "getAuthStore", "Lru/mail/calleridentification/CallsAuthProvider$AuthStore;", "AnonAuthStore", "Companion", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class CallsAnonymAuthStrategy implements CallsAuthStrategy {

    @NotNull
    public static final String ANON_COOKIE_NAME = "anon_user";

    @NotNull
    public static final String CSRF_COOKIE_NAME = "callsapi_csrf";

    @Nullable
    private volatile AnonAuthStore authStore;
    public static final int $stable = 8;

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u001b\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\r\u0010\r\u001a\u00020\u0003H\u0000¢\u0006\u0002\b\u000eJ\r\u0010\u000f\u001a\u00020\u0010H\u0000¢\u0006\u0002\b\u0011J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018HÖ\u0003J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\b\"\u0004\b\f\u0010\n¨\u0006\u001c"}, d2 = {"Lru/mail/data/cmd/server/calls/CallsAnonymAuthStrategy$AnonAuthStore;", "Lru/mail/calleridentification/CallsAuthProvider$AuthStore;", "anonToken", "", "csrfToken", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getAnonToken", "()Ljava/lang/String;", "setAnonToken", "(Ljava/lang/String;)V", "getCsrfToken", "setCsrfToken", "composeCookie", "composeCookie$mail_app_mail_ruRelease", ClientCookie.DISCARD_ATTR, "", "discard$mail_app_mail_ruRelease", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nCallsAnonymAuthStrategy.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CallsAnonymAuthStrategy.kt\nru/mail/data/cmd/server/calls/CallsAnonymAuthStrategy$AnonAuthStore\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,67:1\n774#2:68\n865#2,2:69\n*S KotlinDebug\n*F\n+ 1 CallsAnonymAuthStrategy.kt\nru/mail/data/cmd/server/calls/CallsAnonymAuthStrategy$AnonAuthStore\n*L\n52#1:68\n52#1:69,2\n*E\n"})
    public static final /* data */ class AnonAuthStore implements CallsAuthProvider.AuthStore {
        public static final int $stable = 8;

        @NotNull
        private volatile String anonToken;

        @NotNull
        private volatile String csrfToken;

        /* JADX WARN: Multi-variable type inference failed */
        public AnonAuthStore() {
            this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
        }

        public static /* synthetic */ AnonAuthStore copy$default(AnonAuthStore anonAuthStore, String str, String str2, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = anonAuthStore.anonToken;
            }
            if ((i10 & 2) != 0) {
                str2 = anonAuthStore.csrfToken;
            }
            return anonAuthStore.copy(str, str2);
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getAnonToken() {
            return this.anonToken;
        }

        @NotNull
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getCsrfToken() {
            return this.csrfToken;
        }

        @NotNull
        public final String composeCookie$mail_app_mail_ruRelease() {
            ArrayList arrayListArrayListOf = CollectionsKt.arrayListOf(this.anonToken.length() > 0 ? "anon_user=" + this.anonToken : "", this.csrfToken.length() > 0 ? "callsapi_csrf=" + this.csrfToken : "");
            ArrayList arrayList = new ArrayList();
            for (Object obj : arrayListArrayListOf) {
                if (((String) obj).length() > 0) {
                    arrayList.add(obj);
                }
            }
            return CollectionsKt.joinToString$default(arrayList, MailToMyselfParameters.ATTACH_SUBJECT_DELIMITER, null, null, 0, null, null, 62, null);
        }

        @NotNull
        public final AnonAuthStore copy(@NotNull String anonToken, @NotNull String csrfToken) {
            Intrinsics.checkNotNullParameter(anonToken, "anonToken");
            Intrinsics.checkNotNullParameter(csrfToken, "csrfToken");
            return new AnonAuthStore(anonToken, csrfToken);
        }

        public final void discard$mail_app_mail_ruRelease() {
            this.anonToken = "";
            this.csrfToken = "";
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof AnonAuthStore)) {
                return false;
            }
            AnonAuthStore anonAuthStore = (AnonAuthStore) other;
            return Intrinsics.areEqual(this.anonToken, anonAuthStore.anonToken) && Intrinsics.areEqual(this.csrfToken, anonAuthStore.csrfToken);
        }

        @NotNull
        public final String getAnonToken() {
            return this.anonToken;
        }

        @NotNull
        public final String getCsrfToken() {
            return this.csrfToken;
        }

        public int hashCode() {
            return (this.anonToken.hashCode() * 31) + this.csrfToken.hashCode();
        }

        public final void setAnonToken(@NotNull String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.anonToken = str;
        }

        public final void setCsrfToken(@NotNull String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.csrfToken = str;
        }

        @NotNull
        public String toString() {
            return "AnonAuthStore(anonToken=" + this.anonToken + ", csrfToken=" + this.csrfToken + ")";
        }

        public AnonAuthStore(@NotNull String anonToken, @NotNull String csrfToken) {
            Intrinsics.checkNotNullParameter(anonToken, "anonToken");
            Intrinsics.checkNotNullParameter(csrfToken, "csrfToken");
            this.anonToken = anonToken;
            this.csrfToken = csrfToken;
        }

        public /* synthetic */ AnonAuthStore(String str, String str2, int i10, DefaultConstructorMarker defaultConstructorMarker) {
            this((i10 & 1) != 0 ? "" : str, (i10 & 2) != 0 ? "" : str2);
        }
    }

    @Override // ru.mail.calleridentification.CallsAuthStrategy
    public void discardAuthorization() {
        AnonAuthStore anonAuthStore = this.authStore;
        if (anonAuthStore != null) {
            anonAuthStore.discard$mail_app_mail_ruRelease();
        }
        this.authStore = null;
    }

    @Override // ru.mail.calleridentification.CallsAuthStrategy
    @NotNull
    public CallsAuthProvider.AuthStore getAuthStore() {
        AnonAuthStore anonAuthStore = this.authStore;
        return anonAuthStore != null ? anonAuthStore : CallsBaseAuthStrategy.EmptyAuthStore.INSTANCE;
    }

    @Override // ru.mail.calleridentification.CallsAuthStrategy
    @NotNull
    public Map<String, String> getRequestAuthHeaders(@Nullable String authToken) {
        HashMap map = new HashMap();
        AnonAuthStore anonAuthStore = this.authStore;
        if (anonAuthStore != null) {
            String strComposeCookie$mail_app_mail_ruRelease = anonAuthStore.composeCookie$mail_app_mail_ruRelease();
            if (strComposeCookie$mail_app_mail_ruRelease.length() > 0) {
                map.put(SM.COOKIE, strComposeCookie$mail_app_mail_ruRelease);
            }
            String csrfToken = anonAuthStore.getCsrfToken();
            if (csrfToken.length() > 0) {
                map.put("X-CSRF-Token", csrfToken);
            }
        }
        return map;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // ru.mail.calleridentification.CallsAuthStrategy
    public void initialize() {
        this.authStore = new AnonAuthStore(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }
}
