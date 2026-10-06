package com.vk.superapp.js.bridge.events;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import com.huawei.hms.support.feature.result.CommonConstant;
import com.vk.superapp.base.js.bridge.BaseEvent;
import com.vk.superapp.base.js.bridge.Responses;
import com.vk.superapp.browser.ui.VkUiActivityResultDelegate;
import com.vk.superapp.js.bridge.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes20.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001:\u0004\u0002\u0003\u0004\u0005¨\u0006\u0006"}, d2 = {"Lcom/vk/superapp/js/bridge/events/GetAuthToken;", "Lcom/vk/superapp/base/js/bridge/BaseEvent;", "Error", "Parameters", "Response", "Platforms", "js-bridge_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface GetAuthToken extends BaseEvent {

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B+\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tJ\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0007HÆ\u0003J-\u0010\u0013\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001b"}, d2 = {"Lcom/vk/superapp/js/bridge/events/GetAuthToken$Error;", "Lcom/vk/superapp/base/js/bridge/BaseEvent$Error;", "type", "", "authError", "Lcom/vk/superapp/base/js/bridge/Responses$AuthError;", "clientError", "Lcom/vk/superapp/base/js/bridge/Responses$ClientError;", "<init>", "(Ljava/lang/String;Lcom/vk/superapp/base/js/bridge/Responses$AuthError;Lcom/vk/superapp/base/js/bridge/Responses$ClientError;)V", "getType", "()Ljava/lang/String;", "getAuthError", "()Lcom/vk/superapp/base/js/bridge/Responses$AuthError;", "getClientError", "()Lcom/vk/superapp/base/js/bridge/Responses$ClientError;", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "js-bridge_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final /* data */ class Error implements BaseEvent.Error {

        @SerializedName("auth_error")
        @Nullable
        private final Responses.AuthError authError;

        @SerializedName("client_error")
        @Nullable
        private final Responses.ClientError clientError;

        @SerializedName("type")
        @Nullable
        private final String type;

        public Error() {
            this(null, null, null, 7, null);
        }

        public static /* synthetic */ Error copy$default(Error error, String str, Responses.AuthError authError, Responses.ClientError clientError, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = error.type;
            }
            if ((i10 & 2) != 0) {
                authError = error.authError;
            }
            if ((i10 & 4) != 0) {
                clientError = error.clientError;
            }
            return error.copy(str, authError, clientError);
        }

        @Nullable
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getType() {
            return this.type;
        }

        @Nullable
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final Responses.AuthError getAuthError() {
            return this.authError;
        }

        @Nullable
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final Responses.ClientError getClientError() {
            return this.clientError;
        }

        @NotNull
        public final Error copy(@Nullable String type, @Nullable Responses.AuthError authError, @Nullable Responses.ClientError clientError) {
            return new Error(type, authError, clientError);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Error)) {
                return false;
            }
            Error error = (Error) other;
            return Intrinsics.areEqual(this.type, error.type) && Intrinsics.areEqual(this.authError, error.authError) && Intrinsics.areEqual(this.clientError, error.clientError);
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
        public final String getType() {
            return this.type;
        }

        public int hashCode() {
            String str = this.type;
            int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
            Responses.AuthError authError = this.authError;
            int iHashCode2 = (iHashCode + (authError == null ? 0 : authError.hashCode())) * 31;
            Responses.ClientError clientError = this.clientError;
            return iHashCode2 + (clientError != null ? clientError.hashCode() : 0);
        }

        @NotNull
        public String toString() {
            return "Error(type=" + this.type + ", authError=" + this.authError + ", clientError=" + this.clientError + ')';
        }

        public Error(@Nullable String str, @Nullable Responses.AuthError authError, @Nullable Responses.ClientError clientError) {
            this.type = str;
            this.authError = authError;
            this.clientError = clientError;
        }

        public /* synthetic */ Error(String str, Responses.AuthError authError, Responses.ClientError clientError, int i10, DefaultConstructorMarker defaultConstructorMarker) {
            this((i10 & 1) != 0 ? "VKWebAppAccessTokenFailed" : str, (i10 & 2) != 0 ? null : authError, (i10 & 4) != 0 ? null : clientError);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0013\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000b\u0010\b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\t\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/vk/superapp/js/bridge/events/GetAuthToken$Platforms;", "", "platformsAll", "Lcom/vk/superapp/js/bridge/Objects$PlatformsAll;", "<init>", "(Lcom/vk/superapp/js/bridge/Objects$PlatformsAll;)V", "getPlatformsAll", "()Lcom/vk/superapp/js/bridge/Objects$PlatformsAll;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "js-bridge_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final /* data */ class Platforms {

        @SerializedName("platforms_all")
        @Nullable
        private final Objects.PlatformsAll platformsAll;

        /* JADX WARN: Multi-variable type inference failed */
        public Platforms() {
            this(null, 1, 0 == true ? 1 : 0);
        }

        public static /* synthetic */ Platforms copy$default(Platforms platforms, Objects.PlatformsAll platformsAll, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                platformsAll = platforms.platformsAll;
            }
            return platforms.copy(platformsAll);
        }

        @Nullable
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Objects.PlatformsAll getPlatformsAll() {
            return this.platformsAll;
        }

        @NotNull
        public final Platforms copy(@Nullable Objects.PlatformsAll platformsAll) {
            return new Platforms(platformsAll);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Platforms) && this.platformsAll == ((Platforms) other).platformsAll;
        }

        @Nullable
        public final Objects.PlatformsAll getPlatformsAll() {
            return this.platformsAll;
        }

        public int hashCode() {
            Objects.PlatformsAll platformsAll = this.platformsAll;
            if (platformsAll == null) {
                return 0;
            }
            return platformsAll.hashCode();
        }

        @NotNull
        public String toString() {
            return "Platforms(platformsAll=" + this.platformsAll + ')';
        }

        public Platforms(@Nullable Objects.PlatformsAll platformsAll) {
            this.platformsAll = platformsAll;
        }

        public /* synthetic */ Platforms(Objects.PlatformsAll platformsAll, int i10, DefaultConstructorMarker defaultConstructorMarker) {
            this((i10 & 1) != 0 ? null : platformsAll);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001:\u0001\u001aB\u0019\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u0003H\u0016J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u001b"}, d2 = {"Lcom/vk/superapp/js/bridge/events/GetAuthToken$Response;", "Lcom/vk/superapp/base/js/bridge/BaseEvent$Response;", "type", "", "data", "Lcom/vk/superapp/js/bridge/events/GetAuthToken$Response$Data;", "<init>", "(Ljava/lang/String;Lcom/vk/superapp/js/bridge/events/GetAuthToken$Response$Data;)V", "getType", "()Ljava/lang/String;", "getData", "()Lcom/vk/superapp/js/bridge/events/GetAuthToken$Response$Data;", "setRequestId", "requestId", "toJson", "Lcom/google/gson/JsonObject;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "Data", "js-bridge_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final /* data */ class Response implements BaseEvent.Response {

        @SerializedName("data")
        @NotNull
        private final Data data;

        @SerializedName("type")
        @NotNull
        private final String type;

        public Response(@NotNull String type, @NotNull Data data) {
            Intrinsics.checkNotNullParameter(type, "type");
            Intrinsics.checkNotNullParameter(data, "data");
            this.type = type;
            this.data = data;
        }

        public static /* synthetic */ Response copy$default(Response response, String str, Data data, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = response.type;
            }
            if ((i10 & 2) != 0) {
                data = response.data;
            }
            return response.copy(str, data);
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
        public final Response copy(@NotNull String type, @NotNull Data data) {
            Intrinsics.checkNotNullParameter(type, "type");
            Intrinsics.checkNotNullParameter(data, "data");
            return new Response(type, data);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Response)) {
                return false;
            }
            Response response = (Response) other;
            return Intrinsics.areEqual(this.type, response.type) && Intrinsics.areEqual(this.data, response.data);
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

        @Override // com.vk.superapp.base.js.bridge.BaseEvent.Response
        @NotNull
        public BaseEvent.Response setRequestId(@NotNull String requestId) {
            Intrinsics.checkNotNullParameter(requestId, "requestId");
            return copy$default(this, null, Data.copy$default(this.data, null, null, null, null, requestId, 15, null), 1, null);
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
            return "Response(type=" + this.type + ", data=" + this.data + ')';
        }

        public /* synthetic */ Response(String str, Data data, int i10, DefaultConstructorMarker defaultConstructorMarker) {
            this((i10 & 1) != 0 ? "VKWebAppAccessTokenReceived" : str, data);
        }

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0019\b\u0086\b\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u0010J\u0010\u0010\u0019\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010\u0013J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0003HÆ\u0003JH\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\u001cJ\u0013\u0010\u001d\u001a\u00020\b2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001f\u001a\u00020\u0006HÖ\u0001J\t\u0010 \u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u001a\u0010\u0005\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0007\u001a\u0004\u0018\u00010\b8\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u0014\u001a\u0004\b\u0012\u0010\u0013R\u0018\u0010\t\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\r¨\u0006!"}, d2 = {"Lcom/vk/superapp/js/bridge/events/GetAuthToken$Response$Data;", "", CommonConstant.KEY_ACCESS_TOKEN, "", com.huawei.hms.support.api.entity.common.CommonConstant.ReqAccessTokenParam.SCOPE_LABEL, "expires", "", "status", "", "requestId", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Boolean;Ljava/lang/String;)V", "getAccessToken", "()Ljava/lang/String;", "getScope", "getExpires", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getStatus", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getRequestId", "component1", "component2", "component3", "component4", "component5", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Boolean;Ljava/lang/String;)Lcom/vk/superapp/js/bridge/events/GetAuthToken$Response$Data;", "equals", "other", "hashCode", "toString", "js-bridge_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
        public static final /* data */ class Data {

            @SerializedName("access_token")
            @NotNull
            private final String accessToken;

            @SerializedName("expires")
            @Nullable
            private final Integer expires;

            @SerializedName(VkUiActivityResultDelegate.KEY_REQUEST_ID)
            @Nullable
            private final String requestId;

            @SerializedName(com.huawei.hms.support.api.entity.common.CommonConstant.ReqAccessTokenParam.SCOPE_LABEL)
            @Nullable
            private final String scope;

            @SerializedName("status")
            @Nullable
            private final Boolean status;

            public Data(@NotNull String accessToken, @Nullable String str, @Nullable Integer num, @Nullable Boolean bool, @Nullable String str2) {
                Intrinsics.checkNotNullParameter(accessToken, "accessToken");
                this.accessToken = accessToken;
                this.scope = str;
                this.expires = num;
                this.status = bool;
                this.requestId = str2;
            }

            public static /* synthetic */ Data copy$default(Data data, String str, String str2, Integer num, Boolean bool, String str3, int i10, Object obj) {
                if ((i10 & 1) != 0) {
                    str = data.accessToken;
                }
                if ((i10 & 2) != 0) {
                    str2 = data.scope;
                }
                if ((i10 & 4) != 0) {
                    num = data.expires;
                }
                if ((i10 & 8) != 0) {
                    bool = data.status;
                }
                if ((i10 & 16) != 0) {
                    str3 = data.requestId;
                }
                String str4 = str3;
                Integer num2 = num;
                return data.copy(str, str2, num2, bool, str4);
            }

            @NotNull
            /* JADX INFO: renamed from: component1, reason: from getter */
            public final String getAccessToken() {
                return this.accessToken;
            }

            @Nullable
            /* JADX INFO: renamed from: component2, reason: from getter */
            public final String getScope() {
                return this.scope;
            }

            @Nullable
            /* JADX INFO: renamed from: component3, reason: from getter */
            public final Integer getExpires() {
                return this.expires;
            }

            @Nullable
            /* JADX INFO: renamed from: component4, reason: from getter */
            public final Boolean getStatus() {
                return this.status;
            }

            @Nullable
            /* JADX INFO: renamed from: component5, reason: from getter */
            public final String getRequestId() {
                return this.requestId;
            }

            @NotNull
            public final Data copy(@NotNull String accessToken, @Nullable String scope, @Nullable Integer expires, @Nullable Boolean status, @Nullable String requestId) {
                Intrinsics.checkNotNullParameter(accessToken, "accessToken");
                return new Data(accessToken, scope, expires, status, requestId);
            }

            public boolean equals(@Nullable Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof Data)) {
                    return false;
                }
                Data data = (Data) other;
                return Intrinsics.areEqual(this.accessToken, data.accessToken) && Intrinsics.areEqual(this.scope, data.scope) && Intrinsics.areEqual(this.expires, data.expires) && Intrinsics.areEqual(this.status, data.status) && Intrinsics.areEqual(this.requestId, data.requestId);
            }

            @NotNull
            public final String getAccessToken() {
                return this.accessToken;
            }

            @Nullable
            public final Integer getExpires() {
                return this.expires;
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
                String str = this.scope;
                int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
                Integer num = this.expires;
                int iHashCode3 = (iHashCode2 + (num == null ? 0 : num.hashCode())) * 31;
                Boolean bool = this.status;
                int iHashCode4 = (iHashCode3 + (bool == null ? 0 : bool.hashCode())) * 31;
                String str2 = this.requestId;
                return iHashCode4 + (str2 != null ? str2.hashCode() : 0);
            }

            @NotNull
            public String toString() {
                return "Data(accessToken=" + this.accessToken + ", scope=" + this.scope + ", expires=" + this.expires + ", status=" + this.status + ", requestId=" + this.requestId + ')';
            }

            public /* synthetic */ Data(String str, String str2, Integer num, Boolean bool, String str3, int i10, DefaultConstructorMarker defaultConstructorMarker) {
                this(str, (i10 & 2) != 0 ? null : str2, (i10 & 4) != 0 ? null : num, (i10 & 8) != 0 ? null : bool, (i10 & 16) != 0 ? null : str3);
            }
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0005HÆ\u0003J7\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018HÖ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0005HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\r¨\u0006\u001b"}, d2 = {"Lcom/vk/superapp/js/bridge/events/GetAuthToken$Parameters;", "Lcom/vk/superapp/base/js/bridge/BaseEvent$Parameters;", "appId", "", com.huawei.hms.support.api.entity.common.CommonConstant.ReqAccessTokenParam.SCOPE_LABEL, "", "redirectUrl", "requestId", "<init>", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAppId", "()I", "getScope", "()Ljava/lang/String;", "getRedirectUrl", "getRequestId", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "toString", "js-bridge_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final /* data */ class Parameters implements BaseEvent.Parameters {

        @SerializedName("app_id")
        private final int appId;

        @SerializedName("redirect_url")
        @Nullable
        private final String redirectUrl;

        @SerializedName(VkUiActivityResultDelegate.KEY_REQUEST_ID)
        @Nullable
        private final String requestId;

        @SerializedName(com.huawei.hms.support.api.entity.common.CommonConstant.ReqAccessTokenParam.SCOPE_LABEL)
        @Nullable
        private final String scope;

        public Parameters(int i10, @Nullable String str, @Nullable String str2, @Nullable String str3) {
            this.appId = i10;
            this.scope = str;
            this.redirectUrl = str2;
            this.requestId = str3;
        }

        public static /* synthetic */ Parameters copy$default(Parameters parameters, int i10, String str, String str2, String str3, int i11, Object obj) {
            if ((i11 & 1) != 0) {
                i10 = parameters.appId;
            }
            if ((i11 & 2) != 0) {
                str = parameters.scope;
            }
            if ((i11 & 4) != 0) {
                str2 = parameters.redirectUrl;
            }
            if ((i11 & 8) != 0) {
                str3 = parameters.requestId;
            }
            return parameters.copy(i10, str, str2, str3);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getAppId() {
            return this.appId;
        }

        @Nullable
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getScope() {
            return this.scope;
        }

        @Nullable
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getRedirectUrl() {
            return this.redirectUrl;
        }

        @Nullable
        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getRequestId() {
            return this.requestId;
        }

        @NotNull
        public final Parameters copy(int appId, @Nullable String scope, @Nullable String redirectUrl, @Nullable String requestId) {
            return new Parameters(appId, scope, redirectUrl, requestId);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Parameters)) {
                return false;
            }
            Parameters parameters = (Parameters) other;
            return this.appId == parameters.appId && Intrinsics.areEqual(this.scope, parameters.scope) && Intrinsics.areEqual(this.redirectUrl, parameters.redirectUrl) && Intrinsics.areEqual(this.requestId, parameters.requestId);
        }

        public final int getAppId() {
            return this.appId;
        }

        @Nullable
        public final String getRedirectUrl() {
            return this.redirectUrl;
        }

        @Nullable
        public final String getRequestId() {
            return this.requestId;
        }

        @Nullable
        public final String getScope() {
            return this.scope;
        }

        public int hashCode() {
            int iHashCode = Integer.hashCode(this.appId) * 31;
            String str = this.scope;
            int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
            String str2 = this.redirectUrl;
            int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.requestId;
            return iHashCode3 + (str3 != null ? str3.hashCode() : 0);
        }

        @NotNull
        public String toString() {
            return "Parameters(appId=" + this.appId + ", scope=" + this.scope + ", redirectUrl=" + this.redirectUrl + ", requestId=" + this.requestId + ')';
        }

        public /* synthetic */ Parameters(int i10, String str, String str2, String str3, int i11, DefaultConstructorMarker defaultConstructorMarker) {
            this(i10, (i11 & 2) != 0 ? null : str, (i11 & 4) != 0 ? null : str2, (i11 & 8) != 0 ? null : str3);
        }
    }
}
