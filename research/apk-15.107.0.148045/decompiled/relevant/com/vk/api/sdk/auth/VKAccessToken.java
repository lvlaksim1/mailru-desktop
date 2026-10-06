package com.vk.api.sdk.auth;

import android.os.Bundle;
import com.huawei.hms.support.feature.result.CommonConstant;
import com.vk.api.sdk.VKKeyValueStorage;
import com.vk.auth.restore.RestoreConstants;
import com.vk.dto.common.id.UserId;
import com.vk.dto.common.id.UserIdKt;
import com.vk.lists.PaginationHelper;
import com.vk.usersstore.blockstore.deletereceiver.BlockstoreDeleteReceiver;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import kotlinx.coroutines.debug.internal.DebugCoroutineInfoImplKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 /2\u00020\u0001:\u0001/B\u001d\u0012\u0014\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006B;\b\u0016\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0004\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010¢\u0006\u0004\b\u0005\u0010\u0011J\u000e\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020*J\u000e\u0010'\u001a\u00020(2\u0006\u0010+\u001a\u00020,J\u0016\u0010-\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003H\u0002J\b\u0010.\u001a\u00020\u0004H\u0016R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\t\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0015R\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0013\u0010\u0019\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0015R\u0013\u0010\u001b\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0015R\u0013\u0010\u001d\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0015R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u000e\u0010!\u001a\u00020\"X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010#\u001a\u00020\"8F¢\u0006\u0006\u001a\u0004\b#\u0010$R\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&¨\u00060"}, d2 = {"Lcom/vk/api/sdk/auth/VKAccessToken;", "", "params", "", "", "<init>", "(Ljava/util/Map;)V", BlockstoreDeleteReceiver.PARAM_USER_ID, "Lcom/vk/dto/common/id/UserId;", CommonConstant.KEY_ACCESS_TOKEN, "secret", "expiresInSec", "", "createdMs", "", "utilityTokens", "Lcom/vk/api/sdk/auth/UtilityTokens;", "(Lcom/vk/dto/common/id/UserId;Ljava/lang/String;Ljava/lang/String;IJLcom/vk/api/sdk/auth/UtilityTokens;)V", "getUserId", "()Lcom/vk/dto/common/id/UserId;", "getAccessToken", "()Ljava/lang/String;", "getSecret", "getCreatedMs", "()J", "email", "getEmail", "phone", "getPhone", "phoneAccessKey", "getPhoneAccessKey", "getExpiresInSec", "()I", "httpsRequired", "", "isValid", "()Z", "getUtilityTokens", "()Lcom/vk/api/sdk/auth/UtilityTokens;", "save", "", "bundle", "Landroid/os/Bundle;", "storage", "Lcom/vk/api/sdk/VKKeyValueStorage;", "toMap", "toString", "Companion", "core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nVKAccessToken.kt\nKotlin\n*S Kotlin\n*F\n+ 1 VKAccessToken.kt\ncom/vk/api/sdk/auth/VKAccessToken\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,185:1\n1#2:186\n*E\n"})
public final class VKAccessToken {

    @NotNull
    private static final String ACCESS_TOKEN = "access_token";

    @NotNull
    private static final String CREATED = "created";

    @NotNull
    private static final String EMAIL = "email";

    @NotNull
    private static final String EXPIRES_IN = "expires_in";

    @NotNull
    private static final String PHONE = "phone";

    @NotNull
    private static final String SECRET = "secret";

    @NotNull
    private static final String USER_ID = "user_id";

    @NotNull
    private final String accessToken;
    private final long createdMs;

    @Nullable
    private final String email;
    private final int expiresInSec;
    private final boolean httpsRequired;

    @Nullable
    private final String phone;

    @Nullable
    private final String phoneAccessKey;

    @Nullable
    private final String secret;

    @NotNull
    private final UserId userId;

    @NotNull
    private final UtilityTokens utilityTokens;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    @NotNull
    private static final String HTTPS_REQUIRED = "https_required";

    @NotNull
    private static final String VK_ACCESS_TOKEN_KEY = "vk_access_token";

    @NotNull
    private static final String PHONE_ACCESS_KEY = "phone_access_key";

    @NotNull
    private static final String UTILITY_TOKENS = "utility_tokens";

    @NotNull
    private static final List<String> KEYS = CollectionsKt.listOf((Object[]) new String[]{"access_token", "expires_in", "user_id", "secret", HTTPS_REQUIRED, "created", VK_ACCESS_TOKEN_KEY, "email", "phone", PHONE_ACCESS_KEY, UTILITY_TOKENS});

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0014\u001a\u0004\u0018\u00010\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017J\u000e\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bJ\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u001a\u001a\u00020\u001bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00050\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u001c"}, d2 = {"Lcom/vk/api/sdk/auth/VKAccessToken$Companion;", "", "<init>", "()V", "ACCESS_TOKEN", "", "EXPIRES_IN", "USER_ID", "SECRET", "HTTPS_REQUIRED", DebugCoroutineInfoImplKt.CREATED, "VK_ACCESS_TOKEN_KEY", "EMAIL", "PHONE", "PHONE_ACCESS_KEY", "UTILITY_TOKENS", "KEYS", "", "getKEYS", "()Ljava/util/List;", RestoreConstants.DEFAULT_URL_PATH, "Lcom/vk/api/sdk/auth/VKAccessToken;", "bundle", "Landroid/os/Bundle;", "remove", "", "keyValueStorage", "Lcom/vk/api/sdk/VKKeyValueStorage;", "core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nVKAccessToken.kt\nKotlin\n*S Kotlin\n*F\n+ 1 VKAccessToken.kt\ncom/vk/api/sdk/auth/VKAccessToken$Companion\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,185:1\n1869#2,2:186\n*S KotlinDebug\n*F\n+ 1 VKAccessToken.kt\ncom/vk/api/sdk/auth/VKAccessToken$Companion\n*L\n168#1:186,2\n*E\n"})
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final List<String> getKEYS() {
            return VKAccessToken.KEYS;
        }

        public final void remove(@NotNull VKKeyValueStorage keyValueStorage) {
            Intrinsics.checkNotNullParameter(keyValueStorage, "keyValueStorage");
            Iterator<T> it = getKEYS().iterator();
            while (it.hasNext()) {
                keyValueStorage.remove((String) it.next());
            }
        }

        @Nullable
        public final VKAccessToken restore(@Nullable Bundle bundle) {
            Bundle bundle2;
            if (bundle == null || (bundle2 = bundle.getBundle(VKAccessToken.VK_ACCESS_TOKEN_KEY)) == null) {
                return null;
            }
            HashMap map = new HashMap();
            for (String str : bundle2.keySet()) {
                map.put(str, bundle2.getString(str));
            }
            return new VKAccessToken(map);
        }

        private Companion() {
        }

        @Nullable
        public final VKAccessToken restore(@NotNull VKKeyValueStorage keyValueStorage) {
            Intrinsics.checkNotNullParameter(keyValueStorage, "keyValueStorage");
            HashMap map = new HashMap(getKEYS().size());
            for (String str : getKEYS()) {
                String str2 = keyValueStorage.get(str);
                if (str2 != null) {
                    map.put(str, str2);
                }
            }
            if (map.containsKey("access_token") && map.containsKey("user_id")) {
                return new VKAccessToken(map);
            }
            return null;
        }
    }

    /* JADX WARN: Code duplicated, block: B:35:0x00d3  */
    public VKAccessToken(@NotNull Map<String, String> params) {
        long jCurrentTimeMillis;
        int i10;
        UtilityTokens utilityTokens;
        Intrinsics.checkNotNullParameter(params, "params");
        String str = params.get("user_id");
        UserId userId = str != null ? UserIdKt.toUserId(Long.parseLong(str)) : null;
        Intrinsics.checkNotNull(userId);
        this.userId = userId;
        String str2 = params.get("access_token");
        Intrinsics.checkNotNull(str2);
        this.accessToken = str2;
        this.secret = params.get("secret");
        this.httpsRequired = Intrinsics.areEqual("1", params.get(HTTPS_REQUIRED));
        if (params.containsKey("created")) {
            String str3 = params.get("created");
            Intrinsics.checkNotNull(str3);
            jCurrentTimeMillis = Long.parseLong(str3);
        } else {
            jCurrentTimeMillis = System.currentTimeMillis();
        }
        this.createdMs = jCurrentTimeMillis;
        if (params.containsKey("expires_in")) {
            String str4 = params.get("expires_in");
            Intrinsics.checkNotNull(str4);
            i10 = Integer.parseInt(str4);
        } else {
            i10 = -1;
        }
        this.expiresInSec = i10;
        this.email = params.containsKey("email") ? params.get("email") : null;
        this.phone = params.containsKey("phone") ? params.get("phone") : null;
        this.phoneAccessKey = params.containsKey(PHONE_ACCESS_KEY) ? params.get(PHONE_ACCESS_KEY) : null;
        String str5 = params.get(UTILITY_TOKENS);
        if (str5 != null) {
            String str6 = StringsKt.isBlank(str5) ? null : str5;
            utilityTokens = (str6 == null || (utilityTokens = UtilityTokens.INSTANCE.parse(new JSONObject(str6))) == null) ? new UtilityTokens((List<UtilityToken>) CollectionsKt.emptyList()) : utilityTokens;
        }
        this.utilityTokens = utilityTokens;
    }

    private final Map<String, String> toMap() {
        HashMap map = new HashMap();
        map.put("access_token", this.accessToken);
        map.put("secret", this.secret);
        map.put(HTTPS_REQUIRED, this.httpsRequired ? "1" : PaginationHelper.DEFAULT_NEXT_FROM);
        map.put("created", String.valueOf(this.createdMs));
        map.put("expires_in", String.valueOf(this.expiresInSec));
        map.put("user_id", this.userId.toString());
        map.put("email", this.email);
        map.put("phone", this.phone);
        map.put(PHONE_ACCESS_KEY, this.phoneAccessKey);
        map.put(UTILITY_TOKENS, this.utilityTokens.toJSONObject().toString());
        return map;
    }

    @NotNull
    public final String getAccessToken() {
        return this.accessToken;
    }

    public final long getCreatedMs() {
        return this.createdMs;
    }

    @Nullable
    public final String getEmail() {
        return this.email;
    }

    public final int getExpiresInSec() {
        return this.expiresInSec;
    }

    @Nullable
    public final String getPhone() {
        return this.phone;
    }

    @Nullable
    public final String getPhoneAccessKey() {
        return this.phoneAccessKey;
    }

    @Nullable
    public final String getSecret() {
        return this.secret;
    }

    @NotNull
    public final UserId getUserId() {
        return this.userId;
    }

    @NotNull
    public final UtilityTokens getUtilityTokens() {
        return this.utilityTokens;
    }

    public final boolean isValid() {
        int i10 = this.expiresInSec;
        return i10 <= 0 || this.createdMs + ((long) (i10 * 1000)) > System.currentTimeMillis();
    }

    public final void save(@NotNull Bundle bundle) {
        Intrinsics.checkNotNullParameter(bundle, "bundle");
        Bundle bundle2 = new Bundle();
        for (Map.Entry<String, String> entry : toMap().entrySet()) {
            bundle2.putString(entry.getKey(), entry.getValue());
        }
        bundle.putBundle(VK_ACCESS_TOKEN_KEY, bundle2);
    }

    @NotNull
    public String toString() {
        return "VKAccessToken(userId=" + this.userId + ",createdMs=" + this.createdMs + ",email=" + this.email + ",phone=" + this.phone + ",phoneAccessKey=" + this.phoneAccessKey + ",expiresInSec=" + this.expiresInSec + ",isValid=" + isValid() + ",utilityTokens=" + this.utilityTokens + ")";
    }

    public final void save(@NotNull VKKeyValueStorage storage) {
        Intrinsics.checkNotNullParameter(storage, "storage");
        for (Map.Entry<String, String> entry : toMap().entrySet()) {
            storage.putOrRemove(entry.getKey(), entry.getValue());
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public VKAccessToken(@NotNull UserId userId, @NotNull String accessToken, @Nullable String str, int i10, long j10, @NotNull UtilityTokens utilityTokens) {
        this(MapsKt.mapOf(TuplesKt.to("user_id", userId.toString()), TuplesKt.to("access_token", accessToken), TuplesKt.to("secret", str), TuplesKt.to("expires_in", String.valueOf(i10)), TuplesKt.to("created", String.valueOf(j10)), TuplesKt.to(HTTPS_REQUIRED, "1"), TuplesKt.to(UTILITY_TOKENS, utilityTokens.toJSONObject().toString())));
        Intrinsics.checkNotNullParameter(userId, "userId");
        Intrinsics.checkNotNullParameter(accessToken, "accessToken");
        Intrinsics.checkNotNullParameter(utilityTokens, "utilityTokens");
    }
}
