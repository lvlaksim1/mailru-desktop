package ru.mail.data.cmd.server;

import android.accounts.Account;
import android.content.Context;
import androidx.compose.runtime.internal.StabilityInferred;
import com.vk.auth.restore.RestoreConstants;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.EnumsKt;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonBuilder;
import kotlinx.serialization.json.JsonKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.auth.AccountManagerWrapper;
import ru.mail.auth.Authenticator;
import ru.mail.data.entities.MailboxProfile;
import ru.mail.dependencies.NetworkEntryPoint;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.network.NetworkCommand;
import ru.mail.network.UrlPath;
import ru.mail.sdk.BuildConfigVariablesHolder;
import ru.mail.serverapi.MailAuthorizationApiType;
import ru.mail.serverapi.ServerCommandBase;
import ru.mail.serverapi.ServerCommandEmailParams;
import ru.mail.ui.fragments.settings.BaseSettingsActivity;
import ru.mail.util.config.MigrateToPostUtils;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0003\u0010\u0011\u0012B\u0019\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0007\u0010\bJ\b\u0010\t\u001a\u00020\nH\u0014J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\rH\u0014J\b\u0010\u000e\u001a\u00020\u000fH\u0014¨\u0006\u0013"}, d2 = {"Lru/mail/data/cmd/server/UserSecurityCommand;", "Lru/mail/serverapi/ServerCommandBase;", "Lru/mail/serverapi/ServerCommandEmailParams;", "Lru/mail/data/cmd/server/UserSecurityCommand$UserSecurity;", "context", "Landroid/content/Context;", "params", "<init>", "(Landroid/content/Context;Lru/mail/serverapi/ServerCommandEmailParams;)V", "getDefaultApiType", "Lru/mail/serverapi/MailAuthorizationApiType;", "onPostExecuteRequest", "resp", "Lru/mail/network/NetworkCommand$Response;", "onDone", "", "UserSecurity", "ExtraEmail", "ExtraEmailStatus", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "golang", "user", BaseSettingsActivity.KEY_PREF_SECURITY})
public final class UserSecurityCommand extends ServerCommandBase<ServerCommandEmailParams, UserSecurity> {
    public static final int $stable = 8;

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 1)
    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 \"2\u00020\u0001:\u0002!\"B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007B/\b\u0010\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\b\u0006\u0010\fJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\tHÖ\u0001J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001J%\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00002\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0001¢\u0006\u0002\b R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006#"}, d2 = {"Lru/mail/data/cmd/server/UserSecurityCommand$ExtraEmail;", "", "email", "", "status", "Lru/mail/data/cmd/server/UserSecurityCommand$ExtraEmailStatus;", "<init>", "(Ljava/lang/String;Lru/mail/data/cmd/server/UserSecurityCommand$ExtraEmailStatus;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Lru/mail/data/cmd/server/UserSecurityCommand$ExtraEmailStatus;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getEmail", "()Ljava/lang/String;", "getStatus", "()Lru/mail/data/cmd/server/UserSecurityCommand$ExtraEmailStatus;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$mails_release", "$serializer", "Companion", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @Serializable
    public static final /* data */ class ExtraEmail {
        public static final int $stable = 0;

        @NotNull
        private final String email;

        @NotNull
        private final ExtraEmailStatus status;

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        @NotNull
        public static final Companion INSTANCE = new Companion(null);

        @JvmField
        @NotNull
        private static final Lazy<KSerializer<Object>>[] $childSerializers = {null, LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: ru.mail.data.cmd.server.w
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return UserSecurityCommand.ExtraEmail._childSerializers$_anonymous_();
            }
        })};

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lru/mail/data/cmd/server/UserSecurityCommand$ExtraEmail$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lru/mail/data/cmd/server/UserSecurityCommand$ExtraEmail;", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            @NotNull
            public final KSerializer<ExtraEmail> serializer() {
                return UserSecurityCommand$ExtraEmail$$serializer.INSTANCE;
            }

            private Companion() {
            }
        }

        public /* synthetic */ ExtraEmail(int i10, String str, ExtraEmailStatus extraEmailStatus, SerializationConstructorMarker serializationConstructorMarker) {
            if (3 != (i10 & 3)) {
                PluginExceptionsKt.throwMissingFieldException(i10, 3, UserSecurityCommand$ExtraEmail$$serializer.INSTANCE.getDescriptor());
            }
            this.email = str;
            this.status = extraEmailStatus;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final /* synthetic */ KSerializer _childSerializers$_anonymous_() {
            return ExtraEmailStatus.INSTANCE.serializer();
        }

        public static /* synthetic */ ExtraEmail copy$default(ExtraEmail extraEmail, String str, ExtraEmailStatus extraEmailStatus, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = extraEmail.email;
            }
            if ((i10 & 2) != 0) {
                extraEmailStatus = extraEmail.status;
            }
            return extraEmail.copy(str, extraEmailStatus);
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$mails_release(ExtraEmail self, CompositeEncoder output, SerialDescriptor serialDesc) {
            Lazy<KSerializer<Object>>[] lazyArr = $childSerializers;
            output.encodeStringElement(serialDesc, 0, self.email);
            output.encodeSerializableElement(serialDesc, 1, lazyArr[1].getValue(), self.status);
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getEmail() {
            return this.email;
        }

        @NotNull
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final ExtraEmailStatus getStatus() {
            return this.status;
        }

        @NotNull
        public final ExtraEmail copy(@NotNull String email, @NotNull ExtraEmailStatus status) {
            Intrinsics.checkNotNullParameter(email, "email");
            Intrinsics.checkNotNullParameter(status, "status");
            return new ExtraEmail(email, status);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ExtraEmail)) {
                return false;
            }
            ExtraEmail extraEmail = (ExtraEmail) other;
            return Intrinsics.areEqual(this.email, extraEmail.email) && this.status == extraEmail.status;
        }

        @NotNull
        public final String getEmail() {
            return this.email;
        }

        @NotNull
        public final ExtraEmailStatus getStatus() {
            return this.status;
        }

        public int hashCode() {
            return (this.email.hashCode() * 31) + this.status.hashCode();
        }

        @NotNull
        public String toString() {
            return "ExtraEmail(email=" + this.email + ", status=" + this.status + ")";
        }

        public ExtraEmail(@NotNull String email, @NotNull ExtraEmailStatus status) {
            Intrinsics.checkNotNullParameter(email, "email");
            Intrinsics.checkNotNullParameter(status, "status");
            this.email = email;
            this.status = status;
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\b\b\u0087\u0081\u0002\u0018\u0000 \b2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\bB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\t"}, d2 = {"Lru/mail/data/cmd/server/UserSecurityCommand$ExtraEmailStatus;", "", "<init>", "(Ljava/lang/String;I)V", "OK", "TOO_YOUNG", "NON_VERIFIED", "IN_REMOVE_QUEUE", "Companion", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @Serializable
    public enum ExtraEmailStatus {
        OK,
        TOO_YOUNG,
        NON_VERIFIED,
        IN_REMOVE_QUEUE;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        @NotNull
        public static final Companion INSTANCE = new Companion(null);

        @NotNull
        private static final Lazy<KSerializer<Object>> $cachedSerializer$delegate = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: ru.mail.data.cmd.server.x
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return UserSecurityCommand.ExtraEmailStatus._init_$_anonymous_();
            }
        });

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lru/mail/data/cmd/server/UserSecurityCommand$ExtraEmailStatus$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lru/mail/data/cmd/server/UserSecurityCommand$ExtraEmailStatus;", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private final /* synthetic */ KSerializer get$cachedSerializer() {
                return (KSerializer) ExtraEmailStatus.$cachedSerializer$delegate.getValue();
            }

            @NotNull
            public final KSerializer<ExtraEmailStatus> serializer() {
                return get$cachedSerializer();
            }

            private Companion() {
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final /* synthetic */ KSerializer _init_$_anonymous_() {
            return EnumsKt.createAnnotatedEnumSerializer("ru.mail.data.cmd.server.UserSecurityCommand.ExtraEmailStatus", values(), new String[]{"ok", "too_young", "non_verified", "in_remove_queue"}, new Annotation[][]{null, null, null, null}, null);
        }

        @NotNull
        public static EnumEntries<ExtraEmailStatus> getEntries() {
            return $ENTRIES;
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u001a\b\u0087\b\u0018\u0000 %2\u00020\u0001:\u0001%B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\u0004\b\r\u0010\u000eJ\u0006\u0010\u0019\u001a\u00020\tJ\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001e\u001a\u00020\tHÆ\u0003J\u000f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\f0\u000bHÆ\u0003JK\u0010 \u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\t2\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bHÆ\u0001J\u0013\u0010!\u001a\u00020\u00032\b\u0010\"\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010#\u001a\u00020\u0006HÖ\u0001J\t\u0010$\u001a\u00020\tHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018¨\u0006&"}, d2 = {"Lru/mail/data/cmd/server/UserSecurityCommand$UserSecurity;", "", "needChangePassword", "", "disablePassChange", "validPhones", "", "validEmails", "npcType", "", "extraEmails", "", "Lru/mail/data/cmd/server/UserSecurityCommand$ExtraEmail;", "<init>", "(ZZIILjava/lang/String;Ljava/util/List;)V", "getNeedChangePassword", "()Z", "getDisablePassChange", "getValidPhones", "()I", "getValidEmails", "getNpcType", "()Ljava/lang/String;", "getExtraEmails", "()Ljava/util/List;", "serialize", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "hashCode", "toString", "Companion", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nUserSecurityCommand.kt\nKotlin\n*S Kotlin\n*F\n+ 1 UserSecurityCommand.kt\nru/mail/data/cmd/server/UserSecurityCommand$UserSecurity\n+ 2 Json.kt\nkotlinx/serialization/json/Json\n*L\n1#1,136:1\n205#2:137\n*S KotlinDebug\n*F\n+ 1 UserSecurityCommand.kt\nru/mail/data/cmd/server/UserSecurityCommand$UserSecurity\n*L\n84#1:137\n*E\n"})
    public static final /* data */ class UserSecurity {
        private static final int DISABLE_PASS_CHANGE_INDEX = 1;
        private static final int EXTRA_EMAILS_INDEX = 5;
        private static final int FIELDS_NUM = 6;
        private static final int NEED_PASS_CHANGE_INDEX = 0;
        private static final int NPC_TYPE_INDEX = 4;

        @NotNull
        private static final String SEPARATOR = "|";
        private static final int VALID_EMAILS_INDEX = 3;
        private static final int VALID_PHONES_INDEX = 2;
        private final boolean disablePassChange;

        @NotNull
        private final List<ExtraEmail> extraEmails;
        private final boolean needChangePassword;

        @NotNull
        private final String npcType;
        private final int validEmails;
        private final int validPhones;

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        @NotNull
        public static final Companion INSTANCE = new Companion(null);
        public static final int $stable = 8;

        @NotNull
        private static final Json jsonSerializer = JsonKt.Json$default(null, new Function1() { // from class: ru.mail.data.cmd.server.y
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return UserSecurityCommand.UserSecurity.jsonSerializer$lambda$0((JsonBuilder) obj);
            }
        }, 1, null);

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0005J\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\u0006\u0010\u0016\u001a\u00020\u0005R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lru/mail/data/cmd/server/UserSecurityCommand$UserSecurity$Companion;", "", "<init>", "()V", "SEPARATOR", "", "FIELDS_NUM", "", "NEED_PASS_CHANGE_INDEX", "DISABLE_PASS_CHANGE_INDEX", "VALID_PHONES_INDEX", "VALID_EMAILS_INDEX", "NPC_TYPE_INDEX", "EXTRA_EMAILS_INDEX", "jsonSerializer", "Lkotlinx/serialization/json/Json;", "deserialize", "Lru/mail/data/cmd/server/UserSecurityCommand$UserSecurity;", "str", "parseExtraEmails", "", "Lru/mail/data/cmd/server/UserSecurityCommand$ExtraEmail;", "json", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        @SourceDebugExtension({"SMAP\nUserSecurityCommand.kt\nKotlin\n*S Kotlin\n*F\n+ 1 UserSecurityCommand.kt\nru/mail/data/cmd/server/UserSecurityCommand$UserSecurity$Companion\n+ 2 Json.kt\nkotlinx/serialization/json/Json\n*L\n1#1,136:1\n222#2:137\n*S KotlinDebug\n*F\n+ 1 UserSecurityCommand.kt\nru/mail/data/cmd/server/UserSecurityCommand$UserSecurity$Companion\n*L\n119#1:137\n*E\n"})
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            @Nullable
            public final UserSecurity deserialize(@Nullable String str) {
                if (str == null) {
                    return null;
                }
                List listSplit$default = StringsKt.split$default((CharSequence) str, new String[]{"|"}, false, 0, 6, (Object) null);
                if (listSplit$default.size() == 6) {
                    return new UserSecurity(Boolean.parseBoolean((String) listSplit$default.get(0)), Boolean.parseBoolean((String) listSplit$default.get(1)), Integer.parseInt((String) listSplit$default.get(2)), Integer.parseInt((String) listSplit$default.get(3)), (String) listSplit$default.get(4), parseExtraEmails((String) listSplit$default.get(5)));
                }
                return null;
            }

            @NotNull
            public final List<ExtraEmail> parseExtraEmails(@NotNull String json) {
                Intrinsics.checkNotNullParameter(json, "json");
                try {
                    Json json2 = UserSecurity.jsonSerializer;
                    json2.getSerializersModule();
                    return (List) json2.decodeFromString(new ArrayListSerializer(ExtraEmail.INSTANCE.serializer()), json);
                } catch (IllegalArgumentException unused) {
                    return CollectionsKt.emptyList();
                }
            }

            private Companion() {
            }
        }

        public UserSecurity(boolean z10, boolean z11, int i10, int i11, @NotNull String npcType, @NotNull List<ExtraEmail> extraEmails) {
            Intrinsics.checkNotNullParameter(npcType, "npcType");
            Intrinsics.checkNotNullParameter(extraEmails, "extraEmails");
            this.needChangePassword = z10;
            this.disablePassChange = z11;
            this.validPhones = i10;
            this.validEmails = i11;
            this.npcType = npcType;
            this.extraEmails = extraEmails;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ UserSecurity copy$default(UserSecurity userSecurity, boolean z10, boolean z11, int i10, int i11, String str, List list, int i12, Object obj) {
            if ((i12 & 1) != 0) {
                z10 = userSecurity.needChangePassword;
            }
            if ((i12 & 2) != 0) {
                z11 = userSecurity.disablePassChange;
            }
            if ((i12 & 4) != 0) {
                i10 = userSecurity.validPhones;
            }
            if ((i12 & 8) != 0) {
                i11 = userSecurity.validEmails;
            }
            if ((i12 & 16) != 0) {
                str = userSecurity.npcType;
            }
            if ((i12 & 32) != 0) {
                list = userSecurity.extraEmails;
            }
            String str2 = str;
            List list2 = list;
            return userSecurity.copy(z10, z11, i10, i11, str2, list2);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit jsonSerializer$lambda$0(JsonBuilder Json) {
            Intrinsics.checkNotNullParameter(Json, "$this$Json");
            Json.setIgnoreUnknownKeys(true);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final boolean getNeedChangePassword() {
            return this.needChangePassword;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final boolean getDisablePassChange() {
            return this.disablePassChange;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final int getValidPhones() {
            return this.validPhones;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final int getValidEmails() {
            return this.validEmails;
        }

        @NotNull
        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getNpcType() {
            return this.npcType;
        }

        @NotNull
        public final List<ExtraEmail> component6() {
            return this.extraEmails;
        }

        @NotNull
        public final UserSecurity copy(boolean needChangePassword, boolean disablePassChange, int validPhones, int validEmails, @NotNull String npcType, @NotNull List<ExtraEmail> extraEmails) {
            Intrinsics.checkNotNullParameter(npcType, "npcType");
            Intrinsics.checkNotNullParameter(extraEmails, "extraEmails");
            return new UserSecurity(needChangePassword, disablePassChange, validPhones, validEmails, npcType, extraEmails);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof UserSecurity)) {
                return false;
            }
            UserSecurity userSecurity = (UserSecurity) other;
            return this.needChangePassword == userSecurity.needChangePassword && this.disablePassChange == userSecurity.disablePassChange && this.validPhones == userSecurity.validPhones && this.validEmails == userSecurity.validEmails && Intrinsics.areEqual(this.npcType, userSecurity.npcType) && Intrinsics.areEqual(this.extraEmails, userSecurity.extraEmails);
        }

        public final boolean getDisablePassChange() {
            return this.disablePassChange;
        }

        @NotNull
        public final List<ExtraEmail> getExtraEmails() {
            return this.extraEmails;
        }

        public final boolean getNeedChangePassword() {
            return this.needChangePassword;
        }

        @NotNull
        public final String getNpcType() {
            return this.npcType;
        }

        public final int getValidEmails() {
            return this.validEmails;
        }

        public final int getValidPhones() {
            return this.validPhones;
        }

        public int hashCode() {
            return (((((((((Boolean.hashCode(this.needChangePassword) * 31) + Boolean.hashCode(this.disablePassChange)) * 31) + Integer.hashCode(this.validPhones)) * 31) + Integer.hashCode(this.validEmails)) * 31) + this.npcType.hashCode()) * 31) + this.extraEmails.hashCode();
        }

        @NotNull
        public final String serialize() {
            ArrayList arrayList = new ArrayList();
            arrayList.add(0, String.valueOf(this.needChangePassword));
            arrayList.add(1, String.valueOf(this.disablePassChange));
            arrayList.add(2, String.valueOf(this.validPhones));
            arrayList.add(3, String.valueOf(this.validEmails));
            arrayList.add(4, this.npcType);
            Json.Companion companion = Json.INSTANCE;
            List<ExtraEmail> list = this.extraEmails;
            companion.getSerializersModule();
            arrayList.add(5, companion.encodeToString(new ArrayListSerializer(ExtraEmail.INSTANCE.serializer()), list));
            return CollectionsKt.joinToString$default(arrayList, "|", null, null, 0, null, null, 62, null);
        }

        @NotNull
        public String toString() {
            return "UserSecurity(needChangePassword=" + this.needChangePassword + ", disablePassChange=" + this.disablePassChange + ", validPhones=" + this.validPhones + ", validEmails=" + this.validEmails + ", npcType=" + this.npcType + ", extraEmails=" + this.extraEmails + ")";
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UserSecurityCommand(@NotNull Context context, @Nullable ServerCommandEmailParams serverCommandEmailParams) {
        super(context, serverCommandEmailParams, MigrateToPostUtils.is12130Enabled(context));
        Intrinsics.checkNotNullParameter(context, "context");
    }

    @Override // ru.mail.serverapi.ServerCommandBase
    @NotNull
    protected MailAuthorizationApiType getDefaultApiType() {
        return MailAuthorizationApiType.TORNADO_MPOP;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // ru.mail.mailbox.cmd.Command
    protected void onDone() {
        super.onDone();
        if (statusOK() && !isCancelled()) {
            AccountManagerWrapper accountManagerWrapper = Authenticator.getAccountManagerWrapper(getContext());
            String login = ((ServerCommandEmailParams) getParams()).getLogin();
            Intrinsics.checkNotNull(login);
            accountManagerWrapper.setUserData(new Account(login, BuildConfigVariablesHolder.accountType), MailboxProfile.ACCOUNT_KEY_SECURITY_INFO, getOkData().serialize());
        }
        NetworkEntryPoint.Companion companion = NetworkEntryPoint.INSTANCE;
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        RequestListenerManager requestListenerManager = companion.requestListenerManager(context);
        CommandStatus<?> result = getResult();
        Intrinsics.checkNotNullExpressionValue(result, "getResult(...)");
        requestListenerManager.pushResponse(this, result, ((ServerCommandEmailParams) getParams()).getLogin());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    @NotNull
    public UserSecurity onPostExecuteRequest(@NotNull NetworkCommand.Response resp) throws NetworkCommand.PostExecuteException {
        Intrinsics.checkNotNullParameter(resp, "resp");
        try {
            JSONObject jSONObject = new JSONObject(resp.getRespString()).getJSONObject("body");
            int length = jSONObject.getJSONArray("phones").length();
            JSONArray jSONArray = jSONObject.getJSONObject(RestoreConstants.DEFAULT_URL_PATH).getJSONArray("extra_emails");
            boolean zOptBoolean = jSONObject.optBoolean("need_change_password", false);
            String strOptString = jSONObject.optString("need_password_change", "");
            boolean zOptBoolean2 = jSONObject.optBoolean("disable_password_change", false);
            int length2 = jSONArray.length();
            Intrinsics.checkNotNull(strOptString);
            UserSecurity.Companion companion = UserSecurity.INSTANCE;
            String string = jSONArray.toString();
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
            return new UserSecurity(zOptBoolean, zOptBoolean2, length, length2, strOptString, companion.parseExtraEmails(string));
        } catch (JSONException e10) {
            throw new NetworkCommand.PostExecuteException(e10);
        }
    }
}
