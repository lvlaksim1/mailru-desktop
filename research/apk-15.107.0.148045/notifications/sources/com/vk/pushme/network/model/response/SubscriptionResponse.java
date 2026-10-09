package com.vk.pushme.network.model.response;

import com.vk.pushme.network.model.response.SubscriptionResponse;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes19.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0001\u0018\u0000  2\u00020\u0001:\u0004\u001d\u001e\u001f B!\u0012\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bB5\b\u0010\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u000e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b\u0007\u0010\rJ%\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0001¢\u0006\u0002\b\u001cR$\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0005\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0012\u0010\u000f\u001a\u0004\b\u0013\u0010\u0014¨\u0006!"}, d2 = {"Lcom/vk/pushme/network/model/response/SubscriptionResponse;", "", "validateResult", "", "Lcom/vk/pushme/network/model/response/SubscriptionResponse$AccountResult;", "error", "Lcom/vk/pushme/network/model/response/SubscriptionResponse$Error;", "<init>", "(Ljava/util/List;Lcom/vk/pushme/network/model/response/SubscriptionResponse$Error;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/util/List;Lcom/vk/pushme/network/model/response/SubscriptionResponse$Error;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getValidateResult$annotations", "()V", "getValidateResult", "()Ljava/util/List;", "getError$annotations", "getError", "()Lcom/vk/pushme/network/model/response/SubscriptionResponse$Error;", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$push_me_network_release", "AccountResult", "Error", "$serializer", "Companion", "push-me-network_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@Serializable
public final class SubscriptionResponse {

    @NotNull
    private final Error error;

    @Nullable
    private final List<AccountResult> validateResult;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    @JvmField
    @NotNull
    private static final Lazy<KSerializer<Object>>[] $childSerializers = {LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: h5.a
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return SubscriptionResponse._childSerializers$_anonymous_();
        }
    }), null};

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 #2\u00020\u0001:\u0002\"#B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007B-\b\u0010\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\b\u0006\u0010\fJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00052\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001a\u00020\tHÖ\u0001J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001J%\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00002\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0001¢\u0006\u0002\b!R\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0011\u0010\u000e\u001a\u0004\b\u0004\u0010\u0012¨\u0006$"}, d2 = {"Lcom/vk/pushme/network/model/response/SubscriptionResponse$AccountResult;", "", "account", "", "isValid", "", "<init>", "(Ljava/lang/String;Z)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;ZLkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getAccount$annotations", "()V", "getAccount", "()Ljava/lang/String;", "isValid$annotations", "()Z", "component1", "component2", "copy", "equals", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$push_me_network_release", "$serializer", "Companion", "push-me-network_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @Serializable
    public static final /* data */ class AccountResult {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        @NotNull
        public static final Companion INSTANCE = new Companion(null);

        @NotNull
        private final String account;
        private final boolean isValid;

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/vk/pushme/network/model/response/SubscriptionResponse$AccountResult$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/vk/pushme/network/model/response/SubscriptionResponse$AccountResult;", "push-me-network_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            @NotNull
            public final KSerializer<AccountResult> serializer() {
                return SubscriptionResponse$AccountResult$$serializer.INSTANCE;
            }

            private Companion() {
            }
        }

        public /* synthetic */ AccountResult(int i10, String str, boolean z10, SerializationConstructorMarker serializationConstructorMarker) {
            if (3 != (i10 & 3)) {
                PluginExceptionsKt.throwMissingFieldException(i10, 3, SubscriptionResponse$AccountResult$$serializer.INSTANCE.getDescriptor());
            }
            this.account = str;
            this.isValid = z10;
        }

        public static /* synthetic */ AccountResult copy$default(AccountResult accountResult, String str, boolean z10, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = accountResult.account;
            }
            if ((i10 & 2) != 0) {
                z10 = accountResult.isValid;
            }
            return accountResult.copy(str, z10);
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$push_me_network_release(AccountResult self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeStringElement(serialDesc, 0, self.account);
            output.encodeBooleanElement(serialDesc, 1, self.isValid);
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getAccount() {
            return this.account;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final boolean getIsValid() {
            return this.isValid;
        }

        @NotNull
        public final AccountResult copy(@NotNull String account, boolean isValid) {
            Intrinsics.checkNotNullParameter(account, "account");
            return new AccountResult(account, isValid);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof AccountResult)) {
                return false;
            }
            AccountResult accountResult = (AccountResult) other;
            return Intrinsics.areEqual(this.account, accountResult.account) && this.isValid == accountResult.isValid;
        }

        @NotNull
        public final String getAccount() {
            return this.account;
        }

        public int hashCode() {
            return (this.account.hashCode() * 31) + Boolean.hashCode(this.isValid);
        }

        public final boolean isValid() {
            return this.isValid;
        }

        @NotNull
        public String toString() {
            return "AccountResult(account=" + this.account + ", isValid=" + this.isValid + ")";
        }

        public AccountResult(@NotNull String account, boolean z10) {
            Intrinsics.checkNotNullParameter(account, "account");
            this.account = account;
            this.isValid = z10;
        }

        @SerialName("account")
        public static /* synthetic */ void getAccount$annotations() {
        }

        @SerialName("is_valid")
        public static /* synthetic */ void isValid$annotations() {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/vk/pushme/network/model/response/SubscriptionResponse$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/vk/pushme/network/model/response/SubscriptionResponse;", "push-me-network_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final KSerializer<SubscriptionResponse> serializer() {
            return SubscriptionResponse$$serializer.INSTANCE;
        }

        private Companion() {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 $2\u00020\u0001:\u0002#$B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007B-\b\u0010\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u0006\u0010\u000bJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0005HÖ\u0001J%\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u00002\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!H\u0001¢\u0006\u0002\b\"R\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0010\u0010\r\u001a\u0004\b\u0011\u0010\u0012¨\u0006%"}, d2 = {"Lcom/vk/pushme/network/model/response/SubscriptionResponse$Error;", "", "code", "", "message", "", "<init>", "(ILjava/lang/String;)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IILjava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getCode$annotations", "()V", "getCode", "()I", "getMessage$annotations", "getMessage", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$push_me_network_release", "$serializer", "Companion", "push-me-network_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @Serializable
    public static final /* data */ class Error {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        @NotNull
        public static final Companion INSTANCE = new Companion(null);
        private final int code;

        @NotNull
        private final String message;

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/vk/pushme/network/model/response/SubscriptionResponse$Error$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/vk/pushme/network/model/response/SubscriptionResponse$Error;", "push-me-network_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            @NotNull
            public final KSerializer<Error> serializer() {
                return SubscriptionResponse$Error$$serializer.INSTANCE;
            }

            private Companion() {
            }
        }

        public /* synthetic */ Error(int i10, int i11, String str, SerializationConstructorMarker serializationConstructorMarker) {
            if (3 != (i10 & 3)) {
                PluginExceptionsKt.throwMissingFieldException(i10, 3, SubscriptionResponse$Error$$serializer.INSTANCE.getDescriptor());
            }
            this.code = i11;
            this.message = str;
        }

        public static /* synthetic */ Error copy$default(Error error, int i10, String str, int i11, Object obj) {
            if ((i11 & 1) != 0) {
                i10 = error.code;
            }
            if ((i11 & 2) != 0) {
                str = error.message;
            }
            return error.copy(i10, str);
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$push_me_network_release(Error self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeIntElement(serialDesc, 0, self.code);
            output.encodeStringElement(serialDesc, 1, self.message);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getCode() {
            return this.code;
        }

        @NotNull
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getMessage() {
            return this.message;
        }

        @NotNull
        public final Error copy(int code, @NotNull String message) {
            Intrinsics.checkNotNullParameter(message, "message");
            return new Error(code, message);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Error)) {
                return false;
            }
            Error error = (Error) other;
            return this.code == error.code && Intrinsics.areEqual(this.message, error.message);
        }

        public final int getCode() {
            return this.code;
        }

        @NotNull
        public final String getMessage() {
            return this.message;
        }

        public int hashCode() {
            return (Integer.hashCode(this.code) * 31) + this.message.hashCode();
        }

        @NotNull
        public String toString() {
            return "Error(code=" + this.code + ", message=" + this.message + ")";
        }

        public Error(int i10, @NotNull String message) {
            Intrinsics.checkNotNullParameter(message, "message");
            this.code = i10;
            this.message = message;
        }

        @SerialName("code")
        public static /* synthetic */ void getCode$annotations() {
        }

        @SerialName("message")
        public static /* synthetic */ void getMessage$annotations() {
        }
    }

    public /* synthetic */ SubscriptionResponse(int i10, List list, Error error, SerializationConstructorMarker serializationConstructorMarker) {
        if (2 != (i10 & 2)) {
            PluginExceptionsKt.throwMissingFieldException(i10, 2, SubscriptionResponse$$serializer.INSTANCE.getDescriptor());
        }
        if ((i10 & 1) == 0) {
            this.validateResult = null;
        } else {
            this.validateResult = list;
        }
        this.error = error;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final /* synthetic */ KSerializer _childSerializers$_anonymous_() {
        return new ArrayListSerializer(SubscriptionResponse$AccountResult$$serializer.INSTANCE);
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$push_me_network_release(SubscriptionResponse self, CompositeEncoder output, SerialDescriptor serialDesc) {
        Lazy<KSerializer<Object>>[] lazyArr = $childSerializers;
        if (output.shouldEncodeElementDefault(serialDesc, 0) || self.validateResult != null) {
            output.encodeNullableSerializableElement(serialDesc, 0, lazyArr[0].getValue(), self.validateResult);
        }
        output.encodeSerializableElement(serialDesc, 1, SubscriptionResponse$Error$$serializer.INSTANCE, self.error);
    }

    @NotNull
    public final Error getError() {
        return this.error;
    }

    @Nullable
    public final List<AccountResult> getValidateResult() {
        return this.validateResult;
    }

    public SubscriptionResponse(@Nullable List<AccountResult> list, @NotNull Error error) {
        Intrinsics.checkNotNullParameter(error, "error");
        this.validateResult = list;
        this.error = error;
    }

    public /* synthetic */ SubscriptionResponse(List list, Error error, int i10, DefaultConstructorMarker defaultConstructorMarker) {
        this((i10 & 1) != 0 ? null : list, error);
    }

    @SerialName("error")
    public static /* synthetic */ void getError$annotations() {
    }

    @SerialName("validate_result")
    public static /* synthetic */ void getValidateResult$annotations() {
    }
}
