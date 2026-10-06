package com.vk.pushme.network.model.request;

import com.huawei.hms.support.feature.result.CommonConstant;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;
import kotlinx.serialization.json.JsonObject;
import kotlinx.serialization.json.JsonObjectSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.data.cmd.server.ad.RbParams;
import ru.ok.android.api.core.ApiInvocationException;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes19.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\"\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0081\b\u0018\u0000 B2\u00020\u0001:\u0002ABBS\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0004\b\u000e\u0010\u000fBs\b\u0010\u0012\u0006\u0010\u0010\u001a\u00020\r\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012¢\u0006\u0004\b\u000e\u0010\u0013J\t\u0010*\u001a\u00020\u0003HÆ\u0003J\t\u0010+\u001a\u00020\u0003HÆ\u0003J\t\u0010,\u001a\u00020\u0003HÆ\u0003J\t\u0010-\u001a\u00020\u0003HÆ\u0003J\u000b\u0010.\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010/\u001a\u00020\u0003HÆ\u0003J\u000b\u00100\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u00101\u001a\u00020\u000bHÆ\u0003J\t\u00102\u001a\u00020\rHÆ\u0003Jg\u00103\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\rHÆ\u0001J\u0013\u00104\u001a\u0002052\b\u00106\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00107\u001a\u00020\rHÖ\u0001J\t\u00108\u001a\u00020\u0003HÖ\u0001J%\u00109\u001a\u00020:2\u0006\u0010;\u001a\u00020\u00002\u0006\u0010<\u001a\u00020=2\u0006\u0010>\u001a\u00020?H\u0001¢\u0006\u0002\b@R\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017R\u001c\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0018\u0010\u0015\u001a\u0004\b\u0019\u0010\u0017R\u001c\u0010\u0005\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001a\u0010\u0015\u001a\u0004\b\u001b\u0010\u0017R\u001c\u0010\u0006\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001c\u0010\u0015\u001a\u0004\b\u001d\u0010\u0017R\u001e\u0010\u0007\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001e\u0010\u0015\u001a\u0004\b\u001f\u0010\u0017R\u001c\u0010\b\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b \u0010\u0015\u001a\u0004\b!\u0010\u0017R\u001e\u0010\t\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\"\u0010\u0015\u001a\u0004\b#\u0010\u0017R\u001c\u0010\n\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b$\u0010\u0015\u001a\u0004\b%\u0010&R\u001c\u0010\f\u001a\u00020\r8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b'\u0010\u0015\u001a\u0004\b(\u0010)¨\u0006C"}, d2 = {"Lcom/vk/pushme/network/model/request/InternalSubscriptionRequest;", "", "account", "", "application", "transport", "pushToken", CommonConstant.KEY_ACCESS_TOKEN, "androidId", "sdkDeviceId", "settings", "Lkotlinx/serialization/json/JsonObject;", "status", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlinx/serialization/json/JsonObject;I)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlinx/serialization/json/JsonObject;ILkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getAccount$annotations", "()V", "getAccount", "()Ljava/lang/String;", "getApplication$annotations", "getApplication", "getTransport$annotations", "getTransport", "getPushToken$annotations", "getPushToken", "getAccessToken$annotations", "getAccessToken", "getAndroidId$annotations", "getAndroidId", "getSdkDeviceId$annotations", "getSdkDeviceId", "getSettings$annotations", "getSettings", "()Lkotlinx/serialization/json/JsonObject;", "getStatus$annotations", "getStatus", "()I", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$push_me_network_release", "$serializer", "Companion", "push-me-network_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@Serializable
public final /* data */ class InternalSubscriptionRequest {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    @Nullable
    private final String accessToken;

    @NotNull
    private final String account;

    @NotNull
    private final String androidId;

    @NotNull
    private final String application;

    @NotNull
    private final String pushToken;

    @Nullable
    private final String sdkDeviceId;

    @NotNull
    private final JsonObject settings;
    private final int status;

    @NotNull
    private final String transport;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/vk/pushme/network/model/request/InternalSubscriptionRequest$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/vk/pushme/network/model/request/InternalSubscriptionRequest;", "push-me-network_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final KSerializer<InternalSubscriptionRequest> serializer() {
            return InternalSubscriptionRequest$$serializer.INSTANCE;
        }

        private Companion() {
        }
    }

    public /* synthetic */ InternalSubscriptionRequest(int i10, String str, String str2, String str3, String str4, String str5, String str6, String str7, JsonObject jsonObject, int i11, SerializationConstructorMarker serializationConstructorMarker) {
        if (511 != (i10 & ApiInvocationException.ErrorCodes.IDS_BLOCKED)) {
            PluginExceptionsKt.throwMissingFieldException(i10, ApiInvocationException.ErrorCodes.IDS_BLOCKED, InternalSubscriptionRequest$$serializer.INSTANCE.getDescriptor());
        }
        this.account = str;
        this.application = str2;
        this.transport = str3;
        this.pushToken = str4;
        this.accessToken = str5;
        this.androidId = str6;
        this.sdkDeviceId = str7;
        this.settings = jsonObject;
        this.status = i11;
    }

    public static /* synthetic */ InternalSubscriptionRequest copy$default(InternalSubscriptionRequest internalSubscriptionRequest, String str, String str2, String str3, String str4, String str5, String str6, String str7, JsonObject jsonObject, int i10, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            str = internalSubscriptionRequest.account;
        }
        if ((i11 & 2) != 0) {
            str2 = internalSubscriptionRequest.application;
        }
        if ((i11 & 4) != 0) {
            str3 = internalSubscriptionRequest.transport;
        }
        if ((i11 & 8) != 0) {
            str4 = internalSubscriptionRequest.pushToken;
        }
        if ((i11 & 16) != 0) {
            str5 = internalSubscriptionRequest.accessToken;
        }
        if ((i11 & 32) != 0) {
            str6 = internalSubscriptionRequest.androidId;
        }
        if ((i11 & 64) != 0) {
            str7 = internalSubscriptionRequest.sdkDeviceId;
        }
        if ((i11 & 128) != 0) {
            jsonObject = internalSubscriptionRequest.settings;
        }
        if ((i11 & 256) != 0) {
            i10 = internalSubscriptionRequest.status;
        }
        JsonObject jsonObject2 = jsonObject;
        int i12 = i10;
        String str8 = str6;
        String str9 = str7;
        String str10 = str5;
        String str11 = str3;
        return internalSubscriptionRequest.copy(str, str2, str11, str4, str10, str8, str9, jsonObject2, i12);
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$push_me_network_release(InternalSubscriptionRequest self, CompositeEncoder output, SerialDescriptor serialDesc) {
        output.encodeStringElement(serialDesc, 0, self.account);
        output.encodeStringElement(serialDesc, 1, self.application);
        output.encodeStringElement(serialDesc, 2, self.transport);
        output.encodeStringElement(serialDesc, 3, self.pushToken);
        StringSerializer stringSerializer = StringSerializer.INSTANCE;
        output.encodeNullableSerializableElement(serialDesc, 4, stringSerializer, self.accessToken);
        output.encodeStringElement(serialDesc, 5, self.androidId);
        output.encodeNullableSerializableElement(serialDesc, 6, stringSerializer, self.sdkDeviceId);
        output.encodeSerializableElement(serialDesc, 7, JsonObjectSerializer.INSTANCE, self.settings);
        output.encodeIntElement(serialDesc, 8, self.status);
    }

    @NotNull
    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getAccount() {
        return this.account;
    }

    @NotNull
    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getApplication() {
        return this.application;
    }

    @NotNull
    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getTransport() {
        return this.transport;
    }

    @NotNull
    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getPushToken() {
        return this.pushToken;
    }

    @Nullable
    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getAccessToken() {
        return this.accessToken;
    }

    @NotNull
    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getAndroidId() {
        return this.androidId;
    }

    @Nullable
    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getSdkDeviceId() {
        return this.sdkDeviceId;
    }

    @NotNull
    /* JADX INFO: renamed from: component8, reason: from getter */
    public final JsonObject getSettings() {
        return this.settings;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final int getStatus() {
        return this.status;
    }

    @NotNull
    public final InternalSubscriptionRequest copy(@NotNull String account, @NotNull String application, @NotNull String transport, @NotNull String pushToken, @Nullable String accessToken, @NotNull String androidId, @Nullable String sdkDeviceId, @NotNull JsonObject settings, int status) {
        Intrinsics.checkNotNullParameter(account, "account");
        Intrinsics.checkNotNullParameter(application, "application");
        Intrinsics.checkNotNullParameter(transport, "transport");
        Intrinsics.checkNotNullParameter(pushToken, "pushToken");
        Intrinsics.checkNotNullParameter(androidId, "androidId");
        Intrinsics.checkNotNullParameter(settings, "settings");
        return new InternalSubscriptionRequest(account, application, transport, pushToken, accessToken, androidId, sdkDeviceId, settings, status);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof InternalSubscriptionRequest)) {
            return false;
        }
        InternalSubscriptionRequest internalSubscriptionRequest = (InternalSubscriptionRequest) other;
        return Intrinsics.areEqual(this.account, internalSubscriptionRequest.account) && Intrinsics.areEqual(this.application, internalSubscriptionRequest.application) && Intrinsics.areEqual(this.transport, internalSubscriptionRequest.transport) && Intrinsics.areEqual(this.pushToken, internalSubscriptionRequest.pushToken) && Intrinsics.areEqual(this.accessToken, internalSubscriptionRequest.accessToken) && Intrinsics.areEqual(this.androidId, internalSubscriptionRequest.androidId) && Intrinsics.areEqual(this.sdkDeviceId, internalSubscriptionRequest.sdkDeviceId) && Intrinsics.areEqual(this.settings, internalSubscriptionRequest.settings) && this.status == internalSubscriptionRequest.status;
    }

    @Nullable
    public final String getAccessToken() {
        return this.accessToken;
    }

    @NotNull
    public final String getAccount() {
        return this.account;
    }

    @NotNull
    public final String getAndroidId() {
        return this.androidId;
    }

    @NotNull
    public final String getApplication() {
        return this.application;
    }

    @NotNull
    public final String getPushToken() {
        return this.pushToken;
    }

    @Nullable
    public final String getSdkDeviceId() {
        return this.sdkDeviceId;
    }

    @NotNull
    public final JsonObject getSettings() {
        return this.settings;
    }

    public final int getStatus() {
        return this.status;
    }

    @NotNull
    public final String getTransport() {
        return this.transport;
    }

    public int hashCode() {
        int iHashCode = ((((((this.account.hashCode() * 31) + this.application.hashCode()) * 31) + this.transport.hashCode()) * 31) + this.pushToken.hashCode()) * 31;
        String str = this.accessToken;
        int iHashCode2 = (((iHashCode + (str == null ? 0 : str.hashCode())) * 31) + this.androidId.hashCode()) * 31;
        String str2 = this.sdkDeviceId;
        return ((((iHashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31) + this.settings.hashCode()) * 31) + Integer.hashCode(this.status);
    }

    @NotNull
    public String toString() {
        return "InternalSubscriptionRequest(account=" + this.account + ", application=" + this.application + ", transport=" + this.transport + ", pushToken=" + this.pushToken + ", accessToken=" + this.accessToken + ", androidId=" + this.androidId + ", sdkDeviceId=" + this.sdkDeviceId + ", settings=" + this.settings + ", status=" + this.status + ")";
    }

    public InternalSubscriptionRequest(@NotNull String account, @NotNull String application, @NotNull String transport, @NotNull String pushToken, @Nullable String str, @NotNull String androidId, @Nullable String str2, @NotNull JsonObject settings, int i10) {
        Intrinsics.checkNotNullParameter(account, "account");
        Intrinsics.checkNotNullParameter(application, "application");
        Intrinsics.checkNotNullParameter(transport, "transport");
        Intrinsics.checkNotNullParameter(pushToken, "pushToken");
        Intrinsics.checkNotNullParameter(androidId, "androidId");
        Intrinsics.checkNotNullParameter(settings, "settings");
        this.account = account;
        this.application = application;
        this.transport = transport;
        this.pushToken = pushToken;
        this.accessToken = str;
        this.androidId = androidId;
        this.sdkDeviceId = str2;
        this.settings = settings;
        this.status = i10;
    }

    @SerialName("access_token")
    public static /* synthetic */ void getAccessToken$annotations() {
    }

    @SerialName("account")
    public static /* synthetic */ void getAccount$annotations() {
    }

    @SerialName(RbParams.Default.URL_PARAM_KEY_ANDROID_ID)
    public static /* synthetic */ void getAndroidId$annotations() {
    }

    @SerialName("application")
    public static /* synthetic */ void getApplication$annotations() {
    }

    @SerialName("token")
    public static /* synthetic */ void getPushToken$annotations() {
    }

    @SerialName("sdk_device_id")
    public static /* synthetic */ void getSdkDeviceId$annotations() {
    }

    @SerialName("settings")
    public static /* synthetic */ void getSettings$annotations() {
    }

    @SerialName("status")
    public static /* synthetic */ void getStatus$annotations() {
    }

    @SerialName("platform")
    public static /* synthetic */ void getTransport$annotations() {
    }
}
