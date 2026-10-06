package com.vk.superapp.auth.js.bridge.api.events;

import a.ipaegdirbsjhtuakvmoca;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import com.huawei.hms.support.feature.result.CommonConstant;
import com.vk.superapp.base.js.bridge.BaseEvent;
import com.vk.superapp.base.js.bridge.Responses;
import com.vk.superapp.browser.ui.VkUiActivityResultDelegate;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001:\u0003\u0002\u0003\u0004¨\u0006\u0005"}, d2 = {"Lcom/vk/superapp/auth/js/bridge/api/events/GetAuthToken;", "Lcom/vk/superapp/base/js/bridge/BaseEvent;", "Parameters", "Response", "Error", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface GetAuthToken extends BaseEvent {

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001:\u0001\u0016B\u0019\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0017"}, d2 = {"Lcom/vk/superapp/auth/js/bridge/api/events/GetAuthToken$Error;", "Lcom/vk/superapp/base/js/bridge/BaseEvent$Error;", "type", "", "data", "Lcom/vk/superapp/auth/js/bridge/api/events/GetAuthToken$Error$Data;", "<init>", "(Ljava/lang/String;Lcom/vk/superapp/auth/js/bridge/api/events/GetAuthToken$Error$Data;)V", "getType", "()Ljava/lang/String;", "getData", "()Lcom/vk/superapp/auth/js/bridge/api/events/GetAuthToken$Error$Data;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "Data", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final /* data */ class Error implements BaseEvent.Error {

        @SerializedName("data")
        @NotNull
        private final Data data;

        @SerializedName("type")
        @NotNull
        private final String type;

        public Error(@NotNull String type, @NotNull Data data) {
            Intrinsics.checkNotNullParameter(type, "type");
            Intrinsics.checkNotNullParameter(data, "data");
            this.type = type;
            this.data = data;
        }

        public static /* synthetic */ Error copy$default(Error error, String str, Data data, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = error.type;
            }
            if ((i10 & 2) != 0) {
                data = error.data;
            }
            return error.copy(str, data);
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getType() {
            return this.type;
        }

        @NotNull
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final Data getData() {
            return this.data;
        }

        @NotNull
        public final Error copy(@NotNull String type, @NotNull Data data) {
            Intrinsics.checkNotNullParameter(type, "type");
            Intrinsics.checkNotNullParameter(data, "data");
            return new Error(type, data);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Error)) {
                return false;
            }
            Error error = (Error) other;
            return Intrinsics.areEqual(this.type, error.type) && Intrinsics.areEqual(this.data, error.data);
        }

        @NotNull
        public final Data getData() {
            return this.data;
        }

        @NotNull
        public final String getType() {
            return this.type;
        }

        public int hashCode() {
            return this.data.hashCode() + (this.type.hashCode() * 31);
        }

        @NotNull
        public String toString() {
            return "Error(type=" + this.type + ", data=" + this.data + ')';
        }

        public /* synthetic */ Error(String str, Data data, int i10, DefaultConstructorMarker defaultConstructorMarker) {
            this((i10 & 1) != 0 ? "VKWebAppAccessTokenFailed" : str, data);
        }

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001:\u0001 B3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\tHÆ\u0003J7\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\tHÆ\u0001J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cHÖ\u0003J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001J\t\u0010\u001f\u001a\u00020\u0005HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0018\u0010\b\u001a\u0004\u0018\u00010\t8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006!"}, d2 = {"Lcom/vk/superapp/auth/js/bridge/api/events/GetAuthToken$Error$Data;", "Lcom/vk/superapp/base/js/bridge/BaseEvent$Error$Data;", "type", "Lcom/vk/superapp/auth/js/bridge/api/events/GetAuthToken$Error$Data$Type;", "requestId", "", "authError", "Lcom/vk/superapp/base/js/bridge/Responses$AuthError;", "clientError", "Lcom/vk/superapp/base/js/bridge/Responses$ClientError;", "<init>", "(Lcom/vk/superapp/auth/js/bridge/api/events/GetAuthToken$Error$Data$Type;Ljava/lang/String;Lcom/vk/superapp/base/js/bridge/Responses$AuthError;Lcom/vk/superapp/base/js/bridge/Responses$ClientError;)V", "getType", "()Lcom/vk/superapp/auth/js/bridge/api/events/GetAuthToken$Error$Data$Type;", "getRequestId", "()Ljava/lang/String;", "getAuthError", "()Lcom/vk/superapp/base/js/bridge/Responses$AuthError;", "getClientError", "()Lcom/vk/superapp/base/js/bridge/Responses$ClientError;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "", "toString", "Type", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
        public static final /* data */ class Data implements BaseEvent.Error.Data {

            @SerializedName("auth_error")
            @Nullable
            private final Responses.AuthError authError;

            @SerializedName("client_error")
            @Nullable
            private final Responses.ClientError clientError;

            @SerializedName(VkUiActivityResultDelegate.KEY_REQUEST_ID)
            @Nullable
            private final String requestId;

            @SerializedName("type")
            @NotNull
            private final Type type;

            /* JADX WARN: Enum visitor error
            jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v1 com.vk.superapp.auth.js.bridge.api.events.GetAuthToken$Error$Data$Type[], still in use, count: 1, list:
              (r0v1 com.vk.superapp.auth.js.bridge.api.events.GetAuthToken$Error$Data$Type[]) from 0x001a: INVOKE (r0v1 com.vk.superapp.auth.js.bridge.api.events.GetAuthToken$Error$Data$Type[]) STATIC call: kotlin.enums.EnumEntriesKt.enumEntries(java.lang.Enum[]):kotlin.enums.EnumEntries A[MD:<E extends java.lang.Enum<E>>:(E extends java.lang.Enum<E>[]):kotlin.enums.EnumEntries<E extends java.lang.Enum<E>> (m), WRAPPED] (LINE:27)
            	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:164)
            	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:129)
            	at jadx.core.utils.InsnRemover.lambda$unbindInsns$1(InsnRemover.java:101)
            	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
            	at jadx.core.utils.InsnRemover.unbindInsns(InsnRemover.java:100)
            	at jadx.core.utils.InsnRemover.removeAllAndUnbind(InsnRemover.java:257)
            	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:187)
            	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:102)
             */
            /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
            /* JADX INFO: compiled from: ProGuard */
            @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/vk/superapp/auth/js/bridge/api/events/GetAuthToken$Error$Data$Type;", "", "<init>", "(Ljava/lang/String;I)V", "AUTH_ERROR", "CLIENT_ERROR", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
            public static final class Type {
                AUTH_ERROR,
                CLIENT_ERROR;

                private static final /* synthetic */ EnumEntries ipaegdirbsjhtuakvmocb;

                static {
                    ipaegdirbsjhtuakvmocb = EnumEntriesKt.enumEntries(typeArr);
                }

                private Type() {
                    super(str, i);
                }

                @NotNull
                public static EnumEntries<Type> getEntries() {
                    return ipaegdirbsjhtuakvmocb;
                }

                public static Type valueOf(String str) {
                    return (Type) Enum.valueOf(Type.class, str);
                }

                public static Type[] values() {
                    return (Type[]) ipaegdirbsjhtuakvmoca.clone();
                }
            }

            public Data(@NotNull Type type, @Nullable String str, @Nullable Responses.AuthError authError, @Nullable Responses.ClientError clientError) {
                Intrinsics.checkNotNullParameter(type, "type");
                this.type = type;
                this.requestId = str;
                this.authError = authError;
                this.clientError = clientError;
            }

            public static /* synthetic */ Data copy$default(Data data, Type type, String str, Responses.AuthError authError, Responses.ClientError clientError, int i10, Object obj) {
                if ((i10 & 1) != 0) {
                    type = data.type;
                }
                if ((i10 & 2) != 0) {
                    str = data.requestId;
                }
                if ((i10 & 4) != 0) {
                    authError = data.authError;
                }
                if ((i10 & 8) != 0) {
                    clientError = data.clientError;
                }
                return data.copy(type, str, authError, clientError);
            }

            @NotNull
            /* JADX INFO: renamed from: component1, reason: from getter */
            public final Type getType() {
                return this.type;
            }

            @Nullable
            /* JADX INFO: renamed from: component2, reason: from getter */
            public final String getRequestId() {
                return this.requestId;
            }

            @Nullable
            /* JADX INFO: renamed from: component3, reason: from getter */
            public final Responses.AuthError getAuthError() {
                return this.authError;
            }

            @Nullable
            /* JADX INFO: renamed from: component4, reason: from getter */
            public final Responses.ClientError getClientError() {
                return this.clientError;
            }

            @NotNull
            public final Data copy(@NotNull Type type, @Nullable String requestId, @Nullable Responses.AuthError authError, @Nullable Responses.ClientError clientError) {
                Intrinsics.checkNotNullParameter(type, "type");
                return new Data(type, requestId, authError, clientError);
            }

            public boolean equals(@Nullable Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof Data)) {
                    return false;
                }
                Data data = (Data) other;
                return this.type == data.type && Intrinsics.areEqual(this.requestId, data.requestId) && Intrinsics.areEqual(this.authError, data.authError) && Intrinsics.areEqual(this.clientError, data.clientError);
            }

            @Nullable
            public final Responses.AuthError getAuthError() {
                return this.authError;
            }

            @Nullable
            public final Responses.ClientError getClientError() {
                return this.clientError;
            }

            @Nullable
            public final String getRequestId() {
                return this.requestId;
            }

            @NotNull
            public final Type getType() {
                return this.type;
            }

            public int hashCode() {
                int iHashCode = this.type.hashCode() * 31;
                String str = this.requestId;
                int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
                Responses.AuthError authError = this.authError;
                int iHashCode3 = (iHashCode2 + (authError == null ? 0 : authError.hashCode())) * 31;
                Responses.ClientError clientError = this.clientError;
                return iHashCode3 + (clientError != null ? clientError.hashCode() : 0);
            }

            @NotNull
            public String toString() {
                return "Data(type=" + this.type + ", requestId=" + this.requestId + ", authError=" + this.authError + ", clientError=" + this.clientError + ')';
            }

            public /* synthetic */ Data(Type type, String str, Responses.AuthError authError, Responses.ClientError clientError, int i10, DefaultConstructorMarker defaultConstructorMarker) {
                this(type, (i10 & 2) != 0 ? null : str, (i10 & 4) != 0 ? null : authError, (i10 & 8) != 0 ? null : clientError);
            }
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001:\u0001\u001cB#\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0003H\u0016J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003J)\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018HÖ\u0003J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\n¨\u0006\u001d"}, d2 = {"Lcom/vk/superapp/auth/js/bridge/api/events/GetAuthToken$Response;", "Lcom/vk/superapp/base/js/bridge/BaseEvent$Response;", "type", "", "data", "Lcom/vk/superapp/auth/js/bridge/api/events/GetAuthToken$Response$Data;", "requestId", "<init>", "(Ljava/lang/String;Lcom/vk/superapp/auth/js/bridge/api/events/GetAuthToken$Response$Data;Ljava/lang/String;)V", "getType", "()Ljava/lang/String;", "getData", "()Lcom/vk/superapp/auth/js/bridge/api/events/GetAuthToken$Response$Data;", "getRequestId", "setRequestId", "toJson", "Lcom/google/gson/JsonObject;", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "Data", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final /* data */ class Response implements BaseEvent.Response {

        @SerializedName("data")
        @NotNull
        private final Data data;

        @SerializedName(VkUiActivityResultDelegate.KEY_REQUEST_ID)
        @Nullable
        private final String requestId;

        @SerializedName("type")
        @NotNull
        private final String type;

        public Response(@NotNull String type, @NotNull Data data, @Nullable String str) {
            Intrinsics.checkNotNullParameter(type, "type");
            Intrinsics.checkNotNullParameter(data, "data");
            this.type = type;
            this.data = data;
            this.requestId = str;
        }

        public static /* synthetic */ Response copy$default(Response response, String str, Data data, String str2, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = response.type;
            }
            if ((i10 & 2) != 0) {
                data = response.data;
            }
            if ((i10 & 4) != 0) {
                str2 = response.requestId;
            }
            return response.copy(str, data, str2);
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getType() {
            return this.type;
        }

        @NotNull
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final Data getData() {
            return this.data;
        }

        @Nullable
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getRequestId() {
            return this.requestId;
        }

        @NotNull
        public final Response copy(@NotNull String type, @NotNull Data data, @Nullable String requestId) {
            Intrinsics.checkNotNullParameter(type, "type");
            Intrinsics.checkNotNullParameter(data, "data");
            return new Response(type, data, requestId);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Response)) {
                return false;
            }
            Response response = (Response) other;
            return Intrinsics.areEqual(this.type, response.type) && Intrinsics.areEqual(this.data, response.data) && Intrinsics.areEqual(this.requestId, response.requestId);
        }

        @NotNull
        public final Data getData() {
            return this.data;
        }

        @Nullable
        public final String getRequestId() {
            return this.requestId;
        }

        @NotNull
        public final String getType() {
            return this.type;
        }

        public int hashCode() {
            int iHashCode = (this.data.hashCode() + (this.type.hashCode() * 31)) * 31;
            String str = this.requestId;
            return iHashCode + (str == null ? 0 : str.hashCode());
        }

        @Override // com.vk.superapp.base.js.bridge.BaseEvent.Response
        @NotNull
        public BaseEvent.Response setRequestId(@NotNull String requestId) {
            Intrinsics.checkNotNullParameter(requestId, "requestId");
            return copy$default(this, null, null, requestId, 3, null);
        }

        @Override // com.vk.superapp.base.js.bridge.BaseEvent.Response
        @NotNull
        public JsonObject toJson() {
            JsonObject asJsonObject = new Gson().toJsonTree(this).getAsJsonObject();
            Intrinsics.checkNotNullExpressionValue(asJsonObject, "getAsJsonObject(...)");
            return asJsonObject;
        }

        @NotNull
        public String toString() {
            StringBuilder sb2 = new StringBuilder("Response(type=");
            sb2.append(this.type);
            sb2.append(", data=");
            sb2.append(this.data);
            sb2.append(", requestId=");
            return ipaegdirbsjhtuakvmoca.ipaegdirbsjhtuakvmoca(sb2, this.requestId, ')');
        }

        public /* synthetic */ Response(String str, Data data, String str2, int i10, DefaultConstructorMarker defaultConstructorMarker) {
            this((i10 & 1) != 0 ? "VKWebAppAccessTokenReceived" : str, data, str2);
        }

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0019\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BI\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u0012J\u0010\u0010\u001c\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010\u0015J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0003HÆ\u0003JT\u0010\u001e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\u001fJ\u0013\u0010 \u001a\u00020\t2\b\u0010!\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\"\u001a\u00020#HÖ\u0001J\t\u0010$\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u001a\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u0011\u0010\u0012R\u001a\u0010\b\u001a\u0004\u0018\u00010\t8\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u0016\u001a\u0004\b\u0014\u0010\u0015R\u0018\u0010\n\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u000e¨\u0006%"}, d2 = {"Lcom/vk/superapp/auth/js/bridge/api/events/GetAuthToken$Response$Data;", "", CommonConstant.KEY_ACCESS_TOKEN, "", "localAccessToken", com.huawei.hms.support.api.entity.common.CommonConstant.ReqAccessTokenParam.SCOPE_LABEL, "expires", "", "status", "", "requestId", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Boolean;Ljava/lang/String;)V", "getAccessToken", "()Ljava/lang/String;", "getLocalAccessToken", "getScope", "getExpires", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getStatus", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getRequestId", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Boolean;Ljava/lang/String;)Lcom/vk/superapp/auth/js/bridge/api/events/GetAuthToken$Response$Data;", "equals", "other", "hashCode", "", "toString", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
        public static final /* data */ class Data {

            @SerializedName("access_token")
            @NotNull
            private final String accessToken;

            @SerializedName("expires")
            @Nullable
            private final Long expires;

            @SerializedName("local_access_token")
            @Nullable
            private final String localAccessToken;

            @SerializedName(VkUiActivityResultDelegate.KEY_REQUEST_ID)
            @Nullable
            private final String requestId;

            @SerializedName(com.huawei.hms.support.api.entity.common.CommonConstant.ReqAccessTokenParam.SCOPE_LABEL)
            @Nullable
            private final String scope;

            @SerializedName("status")
            @Nullable
            private final Boolean status;

            public Data(@NotNull String accessToken, @Nullable String str, @Nullable String str2, @Nullable Long l10, @Nullable Boolean bool, @Nullable String str3) {
                Intrinsics.checkNotNullParameter(accessToken, "accessToken");
                this.accessToken = accessToken;
                this.localAccessToken = str;
                this.scope = str2;
                this.expires = l10;
                this.status = bool;
                this.requestId = str3;
            }

            public static /* synthetic */ Data copy$default(Data data, String str, String str2, String str3, Long l10, Boolean bool, String str4, int i10, Object obj) {
                if ((i10 & 1) != 0) {
                    str = data.accessToken;
                }
                if ((i10 & 2) != 0) {
                    str2 = data.localAccessToken;
                }
                if ((i10 & 4) != 0) {
                    str3 = data.scope;
                }
                if ((i10 & 8) != 0) {
                    l10 = data.expires;
                }
                if ((i10 & 16) != 0) {
                    bool = data.status;
                }
                if ((i10 & 32) != 0) {
                    str4 = data.requestId;
                }
                Boolean bool2 = bool;
                String str5 = str4;
                return data.copy(str, str2, str3, l10, bool2, str5);
            }

            @NotNull
            /* JADX INFO: renamed from: component1, reason: from getter */
            public final String getAccessToken() {
                return this.accessToken;
            }

            @Nullable
            /* JADX INFO: renamed from: component2, reason: from getter */
            public final String getLocalAccessToken() {
                return this.localAccessToken;
            }

            @Nullable
            /* JADX INFO: renamed from: component3, reason: from getter */
            public final String getScope() {
                return this.scope;
            }

            @Nullable
            /* JADX INFO: renamed from: component4, reason: from getter */
            public final Long getExpires() {
                return this.expires;
            }

            @Nullable
            /* JADX INFO: renamed from: component5, reason: from getter */
            public final Boolean getStatus() {
                return this.status;
            }

            @Nullable
            /* JADX INFO: renamed from: component6, reason: from getter */
            public final String getRequestId() {
                return this.requestId;
            }

            @NotNull
            public final Data copy(@NotNull String accessToken, @Nullable String localAccessToken, @Nullable String scope, @Nullable Long expires, @Nullable Boolean status, @Nullable String requestId) {
                Intrinsics.checkNotNullParameter(accessToken, "accessToken");
                return new Data(accessToken, localAccessToken, scope, expires, status, requestId);
            }

            public boolean equals(@Nullable Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof Data)) {
                    return false;
                }
                Data data = (Data) other;
                return Intrinsics.areEqual(this.accessToken, data.accessToken) && Intrinsics.areEqual(this.localAccessToken, data.localAccessToken) && Intrinsics.areEqual(this.scope, data.scope) && Intrinsics.areEqual(this.expires, data.expires) && Intrinsics.areEqual(this.status, data.status) && Intrinsics.areEqual(this.requestId, data.requestId);
            }

            @NotNull
            public final String getAccessToken() {
                return this.accessToken;
            }

            @Nullable
            public final Long getExpires() {
                return this.expires;
            }

            @Nullable
            public final String getLocalAccessToken() {
                return this.localAccessToken;
            }

            @Nullable
            public final String getRequestId() {
                return this.requestId;
            }

            @Nullable
            public final String getScope() {
                return this.scope;
            }

            @Nullable
            public final Boolean getStatus() {
                return this.status;
            }

            public int hashCode() {
                int iHashCode = this.accessToken.hashCode() * 31;
                String str = this.localAccessToken;
                int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
                String str2 = this.scope;
                int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
                Long l10 = this.expires;
                int iHashCode4 = (iHashCode3 + (l10 == null ? 0 : l10.hashCode())) * 31;
                Boolean bool = this.status;
                int iHashCode5 = (iHashCode4 + (bool == null ? 0 : bool.hashCode())) * 31;
                String str3 = this.requestId;
                return iHashCode5 + (str3 != null ? str3.hashCode() : 0);
            }

            @NotNull
            public String toString() {
                StringBuilder sb2 = new StringBuilder("Data(accessToken=");
                sb2.append(this.accessToken);
                sb2.append(", localAccessToken=");
                sb2.append(this.localAccessToken);
                sb2.append(", scope=");
                sb2.append(this.scope);
                sb2.append(", expires=");
                sb2.append(this.expires);
                sb2.append(", status=");
                sb2.append(this.status);
                sb2.append(", requestId=");
                return ipaegdirbsjhtuakvmoca.ipaegdirbsjhtuakvmoca(sb2, this.requestId, ')');
            }

            public /* synthetic */ Data(String str, String str2, String str3, Long l10, Boolean bool, String str4, int i10, DefaultConstructorMarker defaultConstructorMarker) {
                this(str, (i10 & 2) != 0 ? null : str2, (i10 & 4) != 0 ? null : str3, (i10 & 8) != 0 ? null : l10, (i10 & 16) != 0 ? null : bool, str4);
            }
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0015\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0010\b\u0086\b\u0018\u0000 .2\u00020\u0001:\u0001.BO\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u000e\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u000e\u0010\u000fJ\u0010\u0010\u0010\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0012\u001a\u00020\u0006HÆ\u0003¢\u0006\u0004\b\u0012\u0010\u0013J\u0012\u0010\u0014\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0004\b\u0014\u0010\u0011J\u0012\u0010\u0015\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0004\b\u0015\u0010\u0016J\u0012\u0010\u0017\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0004\b\u0017\u0010\u0011J\u0012\u0010\u0018\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0004\b\u0018\u0010\u0011J^\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0004HÆ\u0001¢\u0006\u0004\b\u0019\u0010\u001aJ\u0010\u0010\u001b\u001a\u00020\u0004HÖ\u0001¢\u0006\u0004\b\u001b\u0010\u0011J\u0010\u0010\u001d\u001a\u00020\u001cHÖ\u0001¢\u0006\u0004\b\u001d\u0010\u001eJ\u001a\u0010!\u001a\u00020\u00062\b\u0010 \u001a\u0004\u0018\u00010\u001fHÖ\u0003¢\u0006\u0004\b!\u0010\"R\u001a\u0010\u0003\u001a\u00020\u00028\u0006X\u0087\u0004¢\u0006\f\n\u0004\b\u0003\u0010#\u001a\u0004\b$\u0010\u000fR\u001a\u0010\u0005\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\f\n\u0004\b\u0005\u0010%\u001a\u0004\b&\u0010\u0011R\u001a\u0010\u0007\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\f\n\u0004\b\u0007\u0010'\u001a\u0004\b(\u0010\u0013R\u001c\u0010\b\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004¢\u0006\f\n\u0004\b\b\u0010%\u001a\u0004\b)\u0010\u0011R\u001c\u0010\t\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\f\n\u0004\b\t\u0010*\u001a\u0004\b+\u0010\u0016R\u001c\u0010\n\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004¢\u0006\f\n\u0004\b\n\u0010%\u001a\u0004\b,\u0010\u0011R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004¢\u0006\f\n\u0004\b\u000b\u0010%\u001a\u0004\b-\u0010\u0011¨\u0006/"}, d2 = {"Lcom/vk/superapp/auth/js/bridge/api/events/GetAuthToken$Parameters;", "Lcom/vk/superapp/base/js/bridge/BaseEvent$Parameters;", "", "appId", "", "requestId", "", "sakIsMainFrame", com.huawei.hms.support.api.entity.common.CommonConstant.ReqAccessTokenParam.SCOPE_LABEL, "appendLocal", "redirectUrl", "sakSourceUrl", "<init>", "(JLjava/lang/String;ZLjava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;)V", "component1", "()J", "component2", "()Ljava/lang/String;", "component3", "()Z", "component4", "component5", "()Ljava/lang/Boolean;", "component6", "component7", "copy", "(JLjava/lang/String;ZLjava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;)Lcom/vk/superapp/auth/js/bridge/api/events/GetAuthToken$Parameters;", "toString", "", "hashCode", "()I", "", "other", "equals", "(Ljava/lang/Object;)Z", "J", "getAppId", "Ljava/lang/String;", "getRequestId", "Z", "getSakIsMainFrame", "getScope", "Ljava/lang/Boolean;", "getAppendLocal", "getRedirectUrl", "getSakSourceUrl", "Companion", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final /* data */ class Parameters implements BaseEvent.Parameters {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        @NotNull
        public static final Companion INSTANCE = new Companion(null);

        @SerializedName("app_id")
        private final long appId;

        @SerializedName("append_local")
        @Nullable
        private final Boolean appendLocal;

        @SerializedName("redirect_url")
        @Nullable
        private final String redirectUrl;

        @SerializedName(VkUiActivityResultDelegate.KEY_REQUEST_ID)
        @NotNull
        private final String requestId;

        @SerializedName("sak_is_main_frame")
        private final boolean sakIsMainFrame;

        @SerializedName("sak_source_url")
        @Nullable
        private final String sakSourceUrl;

        @SerializedName(com.huawei.hms.support.api.entity.common.CommonConstant.ReqAccessTokenParam.SCOPE_LABEL)
        @Nullable
        private final String scope;

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¨\u0006\b"}, d2 = {"Lcom/vk/superapp/auth/js/bridge/api/events/GetAuthToken$Parameters$Companion;", "", "<init>", "()V", "parse", "Lcom/vk/superapp/auth/js/bridge/api/events/GetAuthToken$Parameters;", "data", "", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
        @SourceDebugExtension({"SMAP\nGetAuthToken.kt\nKotlin\n*S Kotlin\n*F\n+ 1 GetAuthToken.kt\ncom/vk/superapp/auth/js/bridge/api/events/GetAuthToken$Parameters$Companion\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,183:1\n1#2:184\n*E\n"})
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            @NotNull
            public final Parameters parse(@Nullable String data) {
                Parameters parametersAccess$addDefaultRequestId = Parameters.access$addDefaultRequestId((Parameters) new Gson().fromJson(data, Parameters.class));
                Parameters.access$checkNonNullParameters(parametersAccess$addDefaultRequestId);
                Parameters.access$checkLimitsAppId(parametersAccess$addDefaultRequestId);
                return parametersAccess$addDefaultRequestId;
            }

            private Companion() {
            }
        }

        public Parameters(long j10, @NotNull String requestId, boolean z10, @Nullable String str, @Nullable Boolean bool, @Nullable String str2, @Nullable String str3) {
            Intrinsics.checkNotNullParameter(requestId, "requestId");
            this.appId = j10;
            this.requestId = requestId;
            this.sakIsMainFrame = z10;
            this.scope = str;
            this.appendLocal = bool;
            this.redirectUrl = str2;
            this.sakSourceUrl = str3;
        }

        public static final Parameters access$addDefaultRequestId(Parameters parameters) {
            return parameters.requestId == null ? copy$default(parameters, 0L, "default_request_id", false, null, null, null, null, 125, null) : parameters;
        }

        public static final void access$checkLimitsAppId(Parameters parameters) {
            if (parameters.appId < 1) {
                throw new IllegalArgumentException("Value appId cannot be less than 1");
            }
        }

        public static final void access$checkNonNullParameters(Parameters parameters) {
            if (parameters.requestId == null) {
                throw new IllegalArgumentException("Value of non-nullable member requestId cannot be\n                        null");
            }
        }

        public static /* synthetic */ Parameters copy$default(Parameters parameters, long j10, String str, boolean z10, String str2, Boolean bool, String str3, String str4, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                j10 = parameters.appId;
            }
            long j11 = j10;
            if ((i10 & 2) != 0) {
                str = parameters.requestId;
            }
            String str5 = str;
            if ((i10 & 4) != 0) {
                z10 = parameters.sakIsMainFrame;
            }
            boolean z11 = z10;
            if ((i10 & 8) != 0) {
                str2 = parameters.scope;
            }
            String str6 = str2;
            if ((i10 & 16) != 0) {
                bool = parameters.appendLocal;
            }
            return parameters.copy(j11, str5, z11, str6, bool, (i10 & 32) != 0 ? parameters.redirectUrl : str3, (i10 & 64) != 0 ? parameters.sakSourceUrl : str4);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final long getAppId() {
            return this.appId;
        }

        @NotNull
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getRequestId() {
            return this.requestId;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final boolean getSakIsMainFrame() {
            return this.sakIsMainFrame;
        }

        @Nullable
        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getScope() {
            return this.scope;
        }

        @Nullable
        /* JADX INFO: renamed from: component5, reason: from getter */
        public final Boolean getAppendLocal() {
            return this.appendLocal;
        }

        @Nullable
        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getRedirectUrl() {
            return this.redirectUrl;
        }

        @Nullable
        /* JADX INFO: renamed from: component7, reason: from getter */
        public final String getSakSourceUrl() {
            return this.sakSourceUrl;
        }

        @NotNull
        public final Parameters copy(long appId, @NotNull String requestId, boolean sakIsMainFrame, @Nullable String scope, @Nullable Boolean appendLocal, @Nullable String redirectUrl, @Nullable String sakSourceUrl) {
            Intrinsics.checkNotNullParameter(requestId, "requestId");
            return new Parameters(appId, requestId, sakIsMainFrame, scope, appendLocal, redirectUrl, sakSourceUrl);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Parameters)) {
                return false;
            }
            Parameters parameters = (Parameters) other;
            return this.appId == parameters.appId && Intrinsics.areEqual(this.requestId, parameters.requestId) && this.sakIsMainFrame == parameters.sakIsMainFrame && Intrinsics.areEqual(this.scope, parameters.scope) && Intrinsics.areEqual(this.appendLocal, parameters.appendLocal) && Intrinsics.areEqual(this.redirectUrl, parameters.redirectUrl) && Intrinsics.areEqual(this.sakSourceUrl, parameters.sakSourceUrl);
        }

        public final long getAppId() {
            return this.appId;
        }

        @Nullable
        public final Boolean getAppendLocal() {
            return this.appendLocal;
        }

        @Nullable
        public final String getRedirectUrl() {
            return this.redirectUrl;
        }

        @NotNull
        public final String getRequestId() {
            return this.requestId;
        }

        public final boolean getSakIsMainFrame() {
            return this.sakIsMainFrame;
        }

        @Nullable
        public final String getSakSourceUrl() {
            return this.sakSourceUrl;
        }

        @Nullable
        public final String getScope() {
            return this.scope;
        }

        public int hashCode() {
            int iHashCode = (Boolean.hashCode(this.sakIsMainFrame) + ((this.requestId.hashCode() + (Long.hashCode(this.appId) * 31)) * 31)) * 31;
            String str = this.scope;
            int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
            Boolean bool = this.appendLocal;
            int iHashCode3 = (iHashCode2 + (bool == null ? 0 : bool.hashCode())) * 31;
            String str2 = this.redirectUrl;
            int iHashCode4 = (iHashCode3 + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.sakSourceUrl;
            return iHashCode4 + (str3 != null ? str3.hashCode() : 0);
        }

        @NotNull
        public String toString() {
            StringBuilder sb2 = new StringBuilder("Parameters(appId=");
            sb2.append(this.appId);
            sb2.append(", requestId=");
            sb2.append(this.requestId);
            sb2.append(", sakIsMainFrame=");
            sb2.append(this.sakIsMainFrame);
            sb2.append(", scope=");
            sb2.append(this.scope);
            sb2.append(", appendLocal=");
            sb2.append(this.appendLocal);
            sb2.append(", redirectUrl=");
            sb2.append(this.redirectUrl);
            sb2.append(", sakSourceUrl=");
            return ipaegdirbsjhtuakvmoca.ipaegdirbsjhtuakvmoca(sb2, this.sakSourceUrl, ')');
        }

        public /* synthetic */ Parameters(long j10, String str, boolean z10, String str2, Boolean bool, String str3, String str4, int i10, DefaultConstructorMarker defaultConstructorMarker) {
            this(j10, str, z10, (i10 & 8) != 0 ? null : str2, (i10 & 16) != 0 ? null : bool, (i10 & 32) != 0 ? null : str3, (i10 & 64) != 0 ? null : str4);
        }
    }
}
