package com.vk.superapp.core.api.models;

import com.huawei.hms.support.feature.result.CommonConstant;
import com.vk.accountmanager.data.AccountManagerRepositoryImpl;
import com.vk.core.serialize.Serializer;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes19.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\b\u0086\b\u0018\u0000 #2\u00020\u0001:\u0001#B+\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\bH\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u0012\u0010\r\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\r\u0010\u000eJ\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u000f\u0010\u000eJ\u0012\u0010\u0010\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u0010\u0010\u000eJ4\u0010\u0011\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0002HÆ\u0001¢\u0006\u0004\b\u0011\u0010\u0012J\u0010\u0010\u0013\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u0013\u0010\u000eJ\u0010\u0010\u0015\u001a\u00020\u0014HÖ\u0001¢\u0006\u0004\b\u0015\u0010\u0016J\u001a\u0010\u001a\u001a\u00020\u00192\b\u0010\u0018\u001a\u0004\u0018\u00010\u0017HÖ\u0003¢\u0006\u0004\b\u001a\u0010\u001bR\u0019\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001e\u0010\u000eR\u0019\u0010\u0004\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u001f\u0010\u001d\u001a\u0004\b \u0010\u000eR\u0019\u0010\u0005\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b!\u0010\u001d\u001a\u0004\b\"\u0010\u000e¨\u0006$"}, d2 = {"Lcom/vk/superapp/core/api/models/BanInfo;", "Lcom/vk/core/serialize/Serializer$StreamParcelableAdapter;", "", "memberName", CommonConstant.KEY_ACCESS_TOKEN, AccountManagerRepositoryImpl.SECRET_ARG, "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "Lcom/vk/core/serialize/Serializer;", "s", "", "serializeTo", "(Lcom/vk/core/serialize/Serializer;)V", "component1", "()Ljava/lang/String;", "component2", "component3", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/vk/superapp/core/api/models/BanInfo;", "toString", "", "hashCode", "()I", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "erockvmoca", "Ljava/lang/String;", "getMemberName", "erockvmocb", "getAccessToken", "erockvmocc", "getSecret", "Companion", "core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nBanInfo.kt\nKotlin\n*S Kotlin\n*F\n+ 1 BanInfo.kt\ncom/vk/superapp/core/api/models/BanInfo\n+ 2 Serializer.kt\ncom/vk/core/serialize/SerializerKt\n*L\n1#1,61:1\n1038#2,4:62\n*S KotlinDebug\n*F\n+ 1 BanInfo.kt\ncom/vk/superapp/core/api/models/BanInfo\n*L\n56#1:62,4\n*E\n"})
public final /* data */ class BanInfo extends Serializer.StreamParcelableAdapter {

    /* JADX INFO: renamed from: erockvmoca, reason: from kotlin metadata */
    @Nullable
    private final String memberName;

    /* JADX INFO: renamed from: erockvmocb, reason: from kotlin metadata */
    @Nullable
    private final String accessToken;

    /* JADX INFO: renamed from: erockvmocc, reason: from kotlin metadata */
    @Nullable
    private final String secret;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    @JvmField
    @NotNull
    public static final Serializer.Creator<BanInfo> CREATOR = new Serializer.Creator<BanInfo>() { // from class: com.vk.superapp.core.api.models.BanInfo$special$$inlined$createSerializer$1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.vk.core.serialize.Serializer.Creator
        public BanInfo createFromSerializer(Serializer s10) {
            Intrinsics.checkNotNullParameter(s10, "s");
            return new BanInfo(s10.readString(), s10.readString(), s10.readString());
        }

        @Override // android.os.Parcelable.Creator
        public BanInfo[] newArray(int size) {
            return new BanInfo[size];
        }
    };

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007R\u0016\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\t8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/vk/superapp/core/api/models/BanInfo$Companion;", "", "<init>", "()V", "parse", "Lcom/vk/superapp/core/api/models/BanInfo;", "json", "Lorg/json/JSONObject;", "CREATOR", "Lcom/vk/core/serialize/Serializer$Creator;", "core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final BanInfo parse(@NotNull JSONObject json) {
            Intrinsics.checkNotNullParameter(json, "json");
            return new BanInfo(json.optString("member_name"), json.optString("access_token"), json.optString(AccountManagerRepositoryImpl.SECRET_ARG));
        }

        private Companion() {
        }
    }

    public BanInfo() {
        this(null, null, null, 7, null);
    }

    public static /* synthetic */ BanInfo copy$default(BanInfo banInfo, String str, String str2, String str3, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = banInfo.memberName;
        }
        if ((i10 & 2) != 0) {
            str2 = banInfo.accessToken;
        }
        if ((i10 & 4) != 0) {
            str3 = banInfo.secret;
        }
        return banInfo.copy(str, str2, str3);
    }

    @JvmStatic
    @NotNull
    public static final BanInfo parse(@NotNull JSONObject jSONObject) {
        return INSTANCE.parse(jSONObject);
    }

    @Nullable
    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getMemberName() {
        return this.memberName;
    }

    @Nullable
    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getAccessToken() {
        return this.accessToken;
    }

    @Nullable
    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getSecret() {
        return this.secret;
    }

    @NotNull
    public final BanInfo copy(@Nullable String memberName, @Nullable String accessToken, @Nullable String secret) {
        return new BanInfo(memberName, accessToken, secret);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BanInfo)) {
            return false;
        }
        BanInfo banInfo = (BanInfo) other;
        return Intrinsics.areEqual(this.memberName, banInfo.memberName) && Intrinsics.areEqual(this.accessToken, banInfo.accessToken) && Intrinsics.areEqual(this.secret, banInfo.secret);
    }

    @Nullable
    public final String getAccessToken() {
        return this.accessToken;
    }

    @Nullable
    public final String getMemberName() {
        return this.memberName;
    }

    @Nullable
    public final String getSecret() {
        return this.secret;
    }

    public int hashCode() {
        String str = this.memberName;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.accessToken;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.secret;
        return iHashCode2 + (str3 != null ? str3.hashCode() : 0);
    }

    @Override // com.vk.core.serialize.Serializer.StreamParcelable
    public void serializeTo(@NotNull Serializer s10) {
        Intrinsics.checkNotNullParameter(s10, "s");
        s10.writeString(this.memberName);
        s10.writeString(this.accessToken);
        s10.writeString(this.secret);
    }

    @NotNull
    public String toString() {
        return "BanInfo(memberName=" + this.memberName + ", accessToken=" + this.accessToken + ", secret=" + this.secret + ')';
    }

    public /* synthetic */ BanInfo(String str, String str2, String str3, int i10, DefaultConstructorMarker defaultConstructorMarker) {
        this((i10 & 1) != 0 ? null : str, (i10 & 2) != 0 ? null : str2, (i10 & 4) != 0 ? null : str3);
    }

    public BanInfo(@Nullable String str, @Nullable String str2, @Nullable String str3) {
        this.memberName = str;
        this.accessToken = str2;
        this.secret = str3;
    }
}
