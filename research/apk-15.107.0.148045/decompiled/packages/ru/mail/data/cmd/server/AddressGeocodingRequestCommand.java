package ru.mail.data.cmd.server;

import android.content.Context;
import androidx.compose.runtime.internal.StabilityInferred;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.logic.content.DataManager;
import ru.mail.logic.content.MailboxContextUtil;
import ru.mail.logic.content.impl.BaseMailboxContext;
import ru.mail.network.HttpMethod;
import ru.mail.network.NetworkCommand;
import ru.mail.network.Param;
import ru.mail.network.UrlPath;
import ru.mail.serverapi.MailAuthorizationApiType;
import ru.mail.serverapi.PostServerRequest;
import ru.mail.serverapi.ServerCommandEmailParams;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0002\u0010\u0011B\u001f\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\b\u0010\u000b\u001a\u00020\fH\u0014J\u0010\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000fH\u0014¨\u0006\u0012"}, d2 = {"Lru/mail/data/cmd/server/AddressGeocodingRequestCommand;", "Lru/mail/serverapi/PostServerRequest;", "Lru/mail/data/cmd/server/AddressGeocodingRequestCommand$Params;", "Lru/mail/data/cmd/server/AddressGeocodingRequestCommand$Address;", "context", "Landroid/content/Context;", "params", "usePostParams", "", "<init>", "(Landroid/content/Context;Lru/mail/data/cmd/server/AddressGeocodingRequestCommand$Params;Z)V", "getDefaultApiType", "Lru/mail/serverapi/MailAuthorizationApiType;", "onPostExecuteRequest", "resp", "Lru/mail/network/NetworkCommand$Response;", "Params", "Address", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "golang", "maps", "decode"})
public final class AddressGeocodingRequestCommand extends PostServerRequest<Params, Address> {
    public static final int $stable = 8;

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 1)
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0013"}, d2 = {"Lru/mail/data/cmd/server/AddressGeocodingRequestCommand$Address;", "", "latitude", "", "longitude", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getLatitude", "()Ljava/lang/String;", "getLongitude", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Address {
        public static final int $stable = 0;

        @NotNull
        private final String latitude;

        @NotNull
        private final String longitude;

        public Address(@NotNull String latitude, @NotNull String longitude) {
            Intrinsics.checkNotNullParameter(latitude, "latitude");
            Intrinsics.checkNotNullParameter(longitude, "longitude");
            this.latitude = latitude;
            this.longitude = longitude;
        }

        public static /* synthetic */ Address copy$default(Address address, String str, String str2, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = address.latitude;
            }
            if ((i10 & 2) != 0) {
                str2 = address.longitude;
            }
            return address.copy(str, str2);
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getLatitude() {
            return this.latitude;
        }

        @NotNull
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getLongitude() {
            return this.longitude;
        }

        @NotNull
        public final Address copy(@NotNull String latitude, @NotNull String longitude) {
            Intrinsics.checkNotNullParameter(latitude, "latitude");
            Intrinsics.checkNotNullParameter(longitude, "longitude");
            return new Address(latitude, longitude);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Address)) {
                return false;
            }
            Address address = (Address) other;
            return Intrinsics.areEqual(this.latitude, address.latitude) && Intrinsics.areEqual(this.longitude, address.longitude);
        }

        @NotNull
        public final String getLatitude() {
            return this.latitude;
        }

        @NotNull
        public final String getLongitude() {
            return this.longitude;
        }

        public int hashCode() {
            return (this.latitude.hashCode() * 31) + this.longitude.hashCode();
        }

        @NotNull
        public String toString() {
            return "Address(latitude=" + this.latitude + ", longitude=" + this.longitude + ")";
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\b\u0007\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\t\u0010\nJ\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0096\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0016R\u0016\u0010\u0006\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0016\u0010\b\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\f¨\u0006\u0014"}, d2 = {"Lru/mail/data/cmd/server/AddressGeocodingRequestCommand$Params;", "Lru/mail/serverapi/ServerCommandEmailParams;", "mailboxContext", "Lru/mail/logic/content/impl/BaseMailboxContext;", "dataManager", "Lru/mail/logic/content/DataManager;", "address", "", "city", "<init>", "(Lru/mail/logic/content/impl/BaseMailboxContext;Lru/mail/logic/content/DataManager;Ljava/lang/String;Ljava/lang/String;)V", "getAddress", "()Ljava/lang/String;", "getCity", "equals", "", "other", "", "hashCode", "", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Params extends ServerCommandEmailParams {
        public static final int $stable = 8;

        @Param(method = HttpMethod.POST, name = "street")
        @NotNull
        private final String address;

        @Param(method = HttpMethod.POST, name = "city")
        @NotNull
        private final String city;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Params(@NotNull BaseMailboxContext mailboxContext, @NotNull DataManager dataManager, @NotNull String address, @NotNull String city) {
            super(MailboxContextUtil.getAccountInfo(mailboxContext, dataManager), MailboxContextUtil.getFolderState(mailboxContext));
            Intrinsics.checkNotNullParameter(mailboxContext, "mailboxContext");
            Intrinsics.checkNotNullParameter(dataManager, "dataManager");
            Intrinsics.checkNotNullParameter(address, "address");
            Intrinsics.checkNotNullParameter(city, "city");
            this.address = address;
            this.city = city;
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!Intrinsics.areEqual(Params.class, other != null ? other.getClass() : null) || !super.equals(other)) {
                return false;
            }
            Intrinsics.checkNotNull(other, "null cannot be cast to non-null type ru.mail.data.cmd.server.AddressGeocodingRequestCommand.Params");
            Params params = (Params) other;
            return Intrinsics.areEqual(this.address, params.address) && Intrinsics.areEqual(this.city, params.city);
        }

        @NotNull
        public final String getAddress() {
            return this.address;
        }

        @NotNull
        public final String getCity() {
            return this.city;
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public int hashCode() {
            return (((super.hashCode() * 31) + this.address.hashCode()) * 31) + this.city.hashCode();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AddressGeocodingRequestCommand(@NotNull Context context, @NotNull Params params, boolean z10) {
        super(context, params, z10);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(params, "params");
    }

    @Override // ru.mail.serverapi.ServerCommandBase
    @NotNull
    protected MailAuthorizationApiType getDefaultApiType() {
        return MailAuthorizationApiType.TORNADO_MPOP;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    @NotNull
    public Address onPostExecuteRequest(@NotNull NetworkCommand.Response resp) throws JSONException {
        Intrinsics.checkNotNullParameter(resp, "resp");
        JSONObject jSONObject = new JSONObject(resp.getRespString()).getJSONObject("body");
        String string = jSONObject.getString("lat");
        String string2 = jSONObject.getString("lon");
        Intrinsics.checkNotNull(string);
        Intrinsics.checkNotNull(string2);
        return new Address(string, string2);
    }
}
