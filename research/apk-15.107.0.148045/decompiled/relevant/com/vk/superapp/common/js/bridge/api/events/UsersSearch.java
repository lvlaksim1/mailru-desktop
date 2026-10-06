package com.vk.superapp.common.js.bridge.api.events;

import a.ipaegdirbsjnommockvmoca;
import a.ipaegdirbsjnommockvmocb;
import a.ipaegdirbsjnommockvmocc;
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
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001:\u0003\u0002\u0003\u0004¨\u0006\u0005"}, d2 = {"Lcom/vk/superapp/common/js/bridge/api/events/UsersSearch;", "Lcom/vk/superapp/base/js/bridge/BaseEvent;", "Parameters", "Response", "Error", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface UsersSearch extends BaseEvent {

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u0086\b\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\t\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\t\u0010\bJ$\u0010\n\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u0002HÆ\u0001¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\f\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\f\u0010\bJ\u0010\u0010\u000e\u001a\u00020\rHÖ\u0001¢\u0006\u0004\b\u000e\u0010\u000fJ\u001a\u0010\u0013\u001a\u00020\u00122\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010HÖ\u0003¢\u0006\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0003\u001a\u00020\u00028\u0006X\u0087\u0004¢\u0006\f\n\u0004\b\u0003\u0010\u0015\u001a\u0004\b\u0016\u0010\bR\u001a\u0010\u0004\u001a\u00020\u00028\u0006X\u0087\u0004¢\u0006\f\n\u0004\b\u0004\u0010\u0015\u001a\u0004\b\u0017\u0010\b¨\u0006\u0019"}, d2 = {"Lcom/vk/superapp/common/js/bridge/api/events/UsersSearch$Parameters;", "Lcom/vk/superapp/base/js/bridge/BaseEvent$Parameters;", "", CommonConstant.KEY_ACCESS_TOKEN, "requestId", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "component1", "()Ljava/lang/String;", "component2", "copy", "(Ljava/lang/String;Ljava/lang/String;)Lcom/vk/superapp/common/js/bridge/api/events/UsersSearch$Parameters;", "toString", "", "hashCode", "()I", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getAccessToken", "getRequestId", "Companion", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final /* data */ class Parameters implements BaseEvent.Parameters {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        @NotNull
        public static final Companion INSTANCE = new Companion(null);

        @SerializedName("access_token")
        @NotNull
        private final String accessToken;

        @SerializedName(VkUiActivityResultDelegate.KEY_REQUEST_ID)
        @NotNull
        private final String requestId;

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¨\u0006\b"}, d2 = {"Lcom/vk/superapp/common/js/bridge/api/events/UsersSearch$Parameters$Companion;", "", "<init>", "()V", "parse", "Lcom/vk/superapp/common/js/bridge/api/events/UsersSearch$Parameters;", "data", "", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
        @SourceDebugExtension({"SMAP\nUsersSearch.kt\nKotlin\n*S Kotlin\n*F\n+ 1 UsersSearch.kt\ncom/vk/superapp/common/js/bridge/api/events/UsersSearch$Parameters$Companion\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,136:1\n1#2:137\n*E\n"})
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            @NotNull
            public final Parameters parse(@Nullable String data) {
                Parameters parametersAccess$addDefaultRequestId = Parameters.access$addDefaultRequestId((Parameters) ipaegdirbsjnommockvmocc.ipaegdirbsjnommockvmoca(data, Parameters.class));
                Parameters.access$checkNonNullParameters(parametersAccess$addDefaultRequestId);
                return parametersAccess$addDefaultRequestId;
            }

            private Companion() {
            }
        }

        public Parameters(@NotNull String accessToken, @NotNull String requestId) {
            Intrinsics.checkNotNullParameter(accessToken, "accessToken");
            Intrinsics.checkNotNullParameter(requestId, "requestId");
            this.accessToken = accessToken;
            this.requestId = requestId;
        }

        public static final Parameters access$addDefaultRequestId(Parameters parameters) {
            return parameters.requestId == null ? copy$default(parameters, null, "default_request_id", 1, null) : parameters;
        }

        public static final void access$checkNonNullParameters(Parameters parameters) {
            if (parameters.accessToken == null) {
                throw new IllegalArgumentException("Value of non-nullable member accessToken cannot be\n                        null");
            }
            if (parameters.requestId == null) {
                throw new IllegalArgumentException("Value of non-nullable member requestId cannot be\n                        null");
            }
        }

        public static /* synthetic */ Parameters copy$default(Parameters parameters, String str, String str2, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = parameters.accessToken;
            }
            if ((i10 & 2) != 0) {
                str2 = parameters.requestId;
            }
            return parameters.copy(str, str2);
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getAccessToken() {
            return this.accessToken;
        }

        @NotNull
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getRequestId() {
            return this.requestId;
        }

        @NotNull
        public final Parameters copy(@NotNull String accessToken, @NotNull String requestId) {
            Intrinsics.checkNotNullParameter(accessToken, "accessToken");
            Intrinsics.checkNotNullParameter(requestId, "requestId");
            return new Parameters(accessToken, requestId);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Parameters)) {
                return false;
            }
            Parameters parameters = (Parameters) other;
            return Intrinsics.areEqual(this.accessToken, parameters.accessToken) && Intrinsics.areEqual(this.requestId, parameters.requestId);
        }

        @NotNull
        public final String getAccessToken() {
            return this.accessToken;
        }

        @NotNull
        public final String getRequestId() {
            return this.requestId;
        }

        public int hashCode() {
            return this.requestId.hashCode() + (this.accessToken.hashCode() * 31);
        }

        @NotNull
        public String toString() {
            StringBuilder sb2 = new StringBuilder("Parameters(accessToken=");
            sb2.append(this.accessToken);
            sb2.append(", requestId=");
            return ipaegdirbsjnommockvmoca.ipaegdirbsjnommockvmoca(sb2, this.requestId, ')');
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001:\u0001\u001cB#\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0003H\u0016J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003J)\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018HÖ\u0003J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\n¨\u0006\u001d"}, d2 = {"Lcom/vk/superapp/common/js/bridge/api/events/UsersSearch$Response;", "Lcom/vk/superapp/base/js/bridge/BaseEvent$Response;", "type", "", "data", "Lcom/vk/superapp/common/js/bridge/api/events/UsersSearch$Response$Data;", "requestId", "<init>", "(Ljava/lang/String;Lcom/vk/superapp/common/js/bridge/api/events/UsersSearch$Response$Data;Ljava/lang/String;)V", "getType", "()Ljava/lang/String;", "getData", "()Lcom/vk/superapp/common/js/bridge/api/events/UsersSearch$Response$Data;", "getRequestId", "setRequestId", "toJson", "Lcom/google/gson/JsonObject;", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "Data", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
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
            return ipaegdirbsjnommockvmoca.ipaegdirbsjnommockvmoca(sb2, this.requestId, ')');
        }

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\tJ\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0005HÆ\u0003J&\u0010\u000f\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010\u0010J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0005HÖ\u0001R\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\tR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0016"}, d2 = {"Lcom/vk/superapp/common/js/bridge/api/events/UsersSearch$Response$Data;", "", "id", "", "requestId", "", "<init>", "(Ljava/lang/Integer;Ljava/lang/String;)V", "getId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getRequestId", "()Ljava/lang/String;", "component1", "component2", "copy", "(Ljava/lang/Integer;Ljava/lang/String;)Lcom/vk/superapp/common/js/bridge/api/events/UsersSearch$Response$Data;", "equals", "", "other", "hashCode", "toString", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
        public static final /* data */ class Data {

            @SerializedName("id")
            @Nullable
            private final Integer id;

            @SerializedName(VkUiActivityResultDelegate.KEY_REQUEST_ID)
            @Nullable
            private final String requestId;

            public Data(@Nullable Integer num, @Nullable String str) {
                this.id = num;
                this.requestId = str;
            }

            public static /* synthetic */ Data copy$default(Data data, Integer num, String str, int i10, Object obj) {
                if ((i10 & 1) != 0) {
                    num = data.id;
                }
                if ((i10 & 2) != 0) {
                    str = data.requestId;
                }
                return data.copy(num, str);
            }

            @Nullable
            /* JADX INFO: renamed from: component1, reason: from getter */
            public final Integer getId() {
                return this.id;
            }

            @Nullable
            /* JADX INFO: renamed from: component2, reason: from getter */
            public final String getRequestId() {
                return this.requestId;
            }

            @NotNull
            public final Data copy(@Nullable Integer id2, @Nullable String requestId) {
                return new Data(id2, requestId);
            }

            public boolean equals(@Nullable Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof Data)) {
                    return false;
                }
                Data data = (Data) other;
                return Intrinsics.areEqual(this.id, data.id) && Intrinsics.areEqual(this.requestId, data.requestId);
            }

            @Nullable
            public final Integer getId() {
                return this.id;
            }

            @Nullable
            public final String getRequestId() {
                return this.requestId;
            }

            public int hashCode() {
                Integer num = this.id;
                int iHashCode = (num == null ? 0 : num.hashCode()) * 31;
                String str = this.requestId;
                return iHashCode + (str != null ? str.hashCode() : 0);
            }

            @NotNull
            public String toString() {
                StringBuilder sb2 = new StringBuilder("Data(id=");
                sb2.append(this.id);
                sb2.append(", requestId=");
                return ipaegdirbsjnommockvmoca.ipaegdirbsjnommockvmoca(sb2, this.requestId, ')');
            }

            public /* synthetic */ Data(Integer num, String str, int i10, DefaultConstructorMarker defaultConstructorMarker) {
                this((i10 & 1) != 0 ? null : num, str);
            }
        }

        public /* synthetic */ Response(String str, Data data, String str2, int i10, DefaultConstructorMarker defaultConstructorMarker) {
            this((i10 & 1) != 0 ? "VKWebAppUserFound" : str, data, str2);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001:\u0001\u0016B\u0019\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0017"}, d2 = {"Lcom/vk/superapp/common/js/bridge/api/events/UsersSearch$Error;", "Lcom/vk/superapp/base/js/bridge/BaseEvent$Error;", "type", "", "data", "Lcom/vk/superapp/common/js/bridge/api/events/UsersSearch$Error$Data;", "<init>", "(Ljava/lang/String;Lcom/vk/superapp/common/js/bridge/api/events/UsersSearch$Error$Data;)V", "getType", "()Ljava/lang/String;", "getData", "()Lcom/vk/superapp/common/js/bridge/api/events/UsersSearch$Error$Data;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "Data", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
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

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001:\u0001\u001bB'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0007HÆ\u0003J+\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0005HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001c"}, d2 = {"Lcom/vk/superapp/common/js/bridge/api/events/UsersSearch$Error$Data;", "Lcom/vk/superapp/base/js/bridge/BaseEvent$Error$Data;", "type", "Lcom/vk/superapp/common/js/bridge/api/events/UsersSearch$Error$Data$Type;", "requestId", "", "clientError", "Lcom/vk/superapp/base/js/bridge/Responses$ClientError;", "<init>", "(Lcom/vk/superapp/common/js/bridge/api/events/UsersSearch$Error$Data$Type;Ljava/lang/String;Lcom/vk/superapp/base/js/bridge/Responses$ClientError;)V", "getType", "()Lcom/vk/superapp/common/js/bridge/api/events/UsersSearch$Error$Data$Type;", "getRequestId", "()Ljava/lang/String;", "getClientError", "()Lcom/vk/superapp/base/js/bridge/Responses$ClientError;", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "Type", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
        public static final /* data */ class Data implements BaseEvent.Error.Data {

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
            jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v1 com.vk.superapp.common.js.bridge.api.events.UsersSearch$Error$Data$Type[], still in use, count: 1, list:
              (r0v1 com.vk.superapp.common.js.bridge.api.events.UsersSearch$Error$Data$Type[]) from 0x0010: INVOKE (r0v1 com.vk.superapp.common.js.bridge.api.events.UsersSearch$Error$Data$Type[]) STATIC call: kotlin.enums.EnumEntriesKt.enumEntries(java.lang.Enum[]):kotlin.enums.EnumEntries A[MD:<E extends java.lang.Enum<E>>:(E extends java.lang.Enum<E>[]):kotlin.enums.EnumEntries<E extends java.lang.Enum<E>> (m), WRAPPED] (LINE:17)
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
            @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004¨\u0006\u0005"}, d2 = {"Lcom/vk/superapp/common/js/bridge/api/events/UsersSearch$Error$Data$Type;", "", "<init>", "(Ljava/lang/String;I)V", "CLIENT_ERROR", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
            public static final class Type {
                CLIENT_ERROR;

                private static final /* synthetic */ EnumEntries ipaegdirbsjnommockvmocb;

                static {
                    ipaegdirbsjnommockvmocb = EnumEntriesKt.enumEntries(typeArr);
                }

                private Type() {
                    super(str, i);
                }

                @NotNull
                public static EnumEntries<Type> getEntries() {
                    return ipaegdirbsjnommockvmocb;
                }

                public static Type valueOf(String str) {
                    return (Type) Enum.valueOf(Type.class, str);
                }

                public static Type[] values() {
                    return (Type[]) ipaegdirbsjnommockvmoca.clone();
                }
            }

            public Data(@NotNull Type type, @Nullable String str, @Nullable Responses.ClientError clientError) {
                Intrinsics.checkNotNullParameter(type, "type");
                this.type = type;
                this.requestId = str;
                this.clientError = clientError;
            }

            public static /* synthetic */ Data copy$default(Data data, Type type, String str, Responses.ClientError clientError, int i10, Object obj) {
                if ((i10 & 1) != 0) {
                    type = data.type;
                }
                if ((i10 & 2) != 0) {
                    str = data.requestId;
                }
                if ((i10 & 4) != 0) {
                    clientError = data.clientError;
                }
                return data.copy(type, str, clientError);
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
            public final Responses.ClientError getClientError() {
                return this.clientError;
            }

            @NotNull
            public final Data copy(@NotNull Type type, @Nullable String requestId, @Nullable Responses.ClientError clientError) {
                Intrinsics.checkNotNullParameter(type, "type");
                return new Data(type, requestId, clientError);
            }

            public boolean equals(@Nullable Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof Data)) {
                    return false;
                }
                Data data = (Data) other;
                return this.type == data.type && Intrinsics.areEqual(this.requestId, data.requestId) && Intrinsics.areEqual(this.clientError, data.clientError);
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
                Responses.ClientError clientError = this.clientError;
                return iHashCode2 + (clientError != null ? clientError.hashCode() : 0);
            }

            @NotNull
            public String toString() {
                StringBuilder sb2 = new StringBuilder("Data(type=");
                sb2.append(this.type);
                sb2.append(", requestId=");
                sb2.append(this.requestId);
                sb2.append(", clientError=");
                return ipaegdirbsjnommockvmocb.ipaegdirbsjnommockvmoca(sb2, this.clientError, ')');
            }

            public /* synthetic */ Data(Type type, String str, Responses.ClientError clientError, int i10, DefaultConstructorMarker defaultConstructorMarker) {
                this(type, (i10 & 2) != 0 ? null : str, (i10 & 4) != 0 ? null : clientError);
            }
        }

        public /* synthetic */ Error(String str, Data data, int i10, DefaultConstructorMarker defaultConstructorMarker) {
            this((i10 & 1) != 0 ? "VKWebAppUsersSearchFailed" : str, data);
        }
    }
}
