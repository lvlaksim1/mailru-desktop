package ru.mail.util.push.pusher;

import android.content.Context;
import androidx.compose.runtime.internal.StabilityInferred;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Result;
import kotlin.collections.CollectionsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import ru.mail.deviceinfo.DeviceIdProvider;
import ru.mail.logic.pushfilters.FilterAccessor;
import ru.mail.portal.app.adapter.notifications.tags.Tag;
import ru.mail.util.log.Log;
import ru.mail.util.push.PusherApplicationType;
import ru.mail.util.push.apps.PusherApplication;
import ru.mail.util.push.model.MultiAccountSettings;
import ru.mail.util.push.provider.BadgeProvider;
import ru.mail.util.push.provider.CapabilitiesProvider;
import ru.mail.util.push.provider.ClientInfoProvider;
import ru.mail.util.push.provider.PushInfoProvider;
import ru.mail.util.push.provider.PusherAppNameProvider;
import ru.mail.util.push.provider.factory.CapabilitiesProviderFactory;
import ru.mail.util.push.provider.impl.ClientInfoProviderImpl;
import ru.mail.util.push.provider.impl.DefaultBadgeProvider;
import ru.mail.util.push.provider.impl.pushme.PushMeAppNameProvider;
import ru.mail.util.push.pusher.network.PushMePusherInterfaceImpl;
import ru.mail.util.push.pusher.network.PushMeV1PusherInterfaceImpl;
import ru.mail.util.push.pusher.network.PusherInterface;
import ru.mail.util.push.pusher.params.PushMeParamsPreparerImpl;
import ru.mail.util.push.pusher.params.PushParamsPreparer;
import ru.mail.util.push.pusher.params.SubscribeParams;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000\u0090\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\b\u0007\u0018\u0000 92\u00020\u0001:\u0006456789B%\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\b\u0010\u0011\u001a\u00020\u0012H\u0014J\b\u0010\u0013\u001a\u00020\u0014H\u0014J\u0018\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0014J,\u0010\u001b\u001a\u00020\u00162\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00032\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001e0\u00032\u0006\u0010\u0019\u001a\u00020\u001aH\u0014J\u0010\u0010\u001f\u001a\u00020 2\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J,\u0010!\u001a\u00020 2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00032\u0006\u0010#\u001a\u00020$2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001e0\u0003H\u0002J\u0010\u0010%\u001a\u00020&2\u0006\u0010#\u001a\u00020$H\u0002J2\u0010'\u001a\b\u0012\u0004\u0012\u00020(0\u00032\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00032\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001e0\u00032\u0006\u0010)\u001a\u00020*H\u0002J\b\u0010+\u001a\u00020,H\u0002J\b\u0010-\u001a\u00020.H\u0002J\u001e\u0010/\u001a\u00020\u00162\f\u00100\u001a\b\u0012\u0004\u0012\u0002010\u00032\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\u0010\u00102\u001a\u00020\u00142\u0006\u00103\u001a\u00020*H\u0002R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006:"}, d2 = {"Lru/mail/util/push/pusher/PushMeTransport;", "Lru/mail/util/push/pusher/BasePusherTransport;", "pushInfoProviders", "", "Lru/mail/util/push/provider/PushInfoProvider;", "context", "Landroid/content/Context;", "deviceIdProvider", "Lru/mail/deviceinfo/DeviceIdProvider;", "<init>", "(Ljava/util/Collection;Landroid/content/Context;Lru/mail/deviceinfo/DeviceIdProvider;)V", "pusherAppNameProvider", "Lru/mail/util/push/provider/impl/pushme/PushMeAppNameProvider;", "internalTransportV1", "Lru/mail/util/push/pusher/network/PushMeV1PusherInterfaceImpl;", "internalTransportV2", "Lru/mail/util/push/pusher/network/PushMePusherInterfaceImpl;", "getPusherAppNameProvider", "Lru/mail/util/push/provider/PusherAppNameProvider;", "getInternalTransport", "Lru/mail/util/push/pusher/network/PusherInterface;", "registerMailAppForPushes", "Lru/mail/util/push/pusher/PusherTransport$Result;", "settings", "Lru/mail/util/push/model/MultiAccountSettings;", "userIdentifier", "", "registerPortalAppsForPushesInternal", "accounts", "apps", "Lru/mail/util/push/apps/PusherApplication;", "prepareMailApp", "Lru/mail/util/push/pusher/PushMeTransport$PrepareParamsResult;", "preparePortalApps", "userIdentifiers", "capabilitiesProvider", "Lru/mail/util/push/provider/CapabilitiesProvider;", "createPushParamsPreparer", "Lru/mail/util/push/pusher/params/PushParamsPreparer;", "createSubscribeParams", "Lru/mail/util/push/pusher/params/SubscribeParams;", "pushMeApiVersion", "Lru/mail/util/push/pusher/PushMeTransport$PushMeApiVersion;", "getBadgeProvider", "Lru/mail/util/push/provider/BadgeProvider;", "getClientInfoProvider", "Lru/mail/util/push/provider/ClientInfoProvider;", "makeNetworkRequestsToSubscribeForPushes", "params", "Lru/mail/util/push/pusher/PushMeTransport$ParamsForNetworkRequest;", "getTransport", "version", "ParamsForNetworkRequest", "PushMeApiVersion", "ParamSplitter", "CompositeResult", "PrepareParamsResult", "Companion", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
@Deprecated(message = "Can be deleted when PushMeSDKPusherTransport will be stable")
@SourceDebugExtension({"SMAP\nPushMeTransport.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PushMeTransport.kt\nru/mail/util/push/pusher/PushMeTransport\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,324:1\n1563#2:325\n1634#2,3:326\n1563#2:329\n1634#2,3:330\n774#2:333\n865#2,2:334\n1563#2:336\n1634#2,3:337\n*S KotlinDebug\n*F\n+ 1 PushMeTransport.kt\nru/mail/util/push/pusher/PushMeTransport\n*L\n150#1:325\n150#1:326,3\n159#1:329\n159#1:330,3\n184#1:333\n184#1:334,2\n188#1:336\n188#1:337,3\n*E\n"})
public final class PushMeTransport extends BasePusherTransport {

    @NotNull
    private final PushMeV1PusherInterfaceImpl internalTransportV1;

    @NotNull
    private final PushMePusherInterfaceImpl internalTransportV2;

    @NotNull
    private final PushMeAppNameProvider pusherAppNameProvider;
    public static final int $stable = 8;

    @NotNull
    private static final Log LOG = Log.INSTANCE.getLog("PushMeTransport");

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes13.dex */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0006J\u0006\u0010\n\u001a\u00020\u0006R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lru/mail/util/push/pusher/PushMeTransport$CompositeResult;", "", "<init>", "()V", "results", "", "Lru/mail/util/push/pusher/PusherTransport$Result;", "collect", "", "result", "getFinalResult", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nPushMeTransport.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PushMeTransport.kt\nru/mail/util/push/pusher/PushMeTransport$CompositeResult\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,324:1\n1740#2,3:325\n*S KotlinDebug\n*F\n+ 1 PushMeTransport.kt\nru/mail/util/push/pusher/PushMeTransport$CompositeResult\n*L\n293#1:325,3\n*E\n"})
    private static final class CompositeResult {

        @NotNull
        private final List<PusherTransport.Result> results = new ArrayList();

        public final void collect(@NotNull PusherTransport.Result result) {
            Intrinsics.checkNotNullParameter(result, "result");
            this.results.add(result);
        }

        @NotNull
        public final PusherTransport.Result getFinalResult() {
            if (this.results.isEmpty()) {
                throw new IllegalStateException("No results have been collected");
            }
            List<PusherTransport.Result> list = this.results;
            if (!(list instanceof Collection) || !list.isEmpty()) {
                Iterator<T> it = list.iterator();
                while (it.hasNext()) {
                    if (!(((PusherTransport.Result) it.next()) instanceof PusherTransport.Result.OK)) {
                        return PusherTransport.Result.UnknownError.INSTANCE;
                    }
                }
            }
            return PusherTransport.Result.OK.INSTANCE;
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes13.dex */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J.\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u00072\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0007J\u001e\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\b2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0007H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lru/mail/util/push/pusher/PushMeTransport$ParamSplitter;", "", "<init>", "()V", "PUSH_ME_LIMIT", "", "split", "", "Lru/mail/util/push/pusher/params/SubscribeParams;", "input", "providers", "Lru/mail/util/push/provider/PushInfoProvider;", "getPointsCount", "params", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class ParamSplitter {

        @NotNull
        public static final ParamSplitter INSTANCE = new ParamSplitter();
        private static final int PUSH_ME_LIMIT = 20;

        private ParamSplitter() {
        }

        private final int getPointsCount(SubscribeParams params, Collection<? extends PushInfoProvider> providers) {
            return params.getUserIdentifiers().size() * providers.size();
        }

        @NotNull
        public final Collection<Collection<SubscribeParams>> split(@NotNull Collection<SubscribeParams> input, @NotNull Collection<? extends PushInfoProvider> providers) {
            Intrinsics.checkNotNullParameter(input, "input");
            Intrinsics.checkNotNullParameter(providers, "providers");
            if (input.isEmpty()) {
                return CollectionsKt.emptyList();
            }
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            int i10 = 0;
            for (SubscribeParams subscribeParams : input) {
                int pointsCount = getPointsCount(subscribeParams, providers);
                if (i10 + pointsCount > 20) {
                    arrayList.add(arrayList2);
                    arrayList2 = new ArrayList();
                    i10 = 0;
                }
                arrayList2.add(subscribeParams);
                i10 += pointsCount;
            }
            arrayList.add(arrayList2);
            return arrayList;
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes13.dex */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lru/mail/util/push/pusher/PushMeTransport$ParamsForNetworkRequest;", "", "requestBody", "Lorg/json/JSONArray;", "apiVersion", "Lru/mail/util/push/pusher/PushMeTransport$PushMeApiVersion;", "<init>", "(Lorg/json/JSONArray;Lru/mail/util/push/pusher/PushMeTransport$PushMeApiVersion;)V", "getRequestBody", "()Lorg/json/JSONArray;", "getApiVersion", "()Lru/mail/util/push/pusher/PushMeTransport$PushMeApiVersion;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final /* data */ class ParamsForNetworkRequest {

        @NotNull
        private final PushMeApiVersion apiVersion;

        @NotNull
        private final JSONArray requestBody;

        public ParamsForNetworkRequest(@NotNull JSONArray requestBody, @NotNull PushMeApiVersion apiVersion) {
            Intrinsics.checkNotNullParameter(requestBody, "requestBody");
            Intrinsics.checkNotNullParameter(apiVersion, "apiVersion");
            this.requestBody = requestBody;
            this.apiVersion = apiVersion;
        }

        public static /* synthetic */ ParamsForNetworkRequest copy$default(ParamsForNetworkRequest paramsForNetworkRequest, JSONArray jSONArray, PushMeApiVersion pushMeApiVersion, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                jSONArray = paramsForNetworkRequest.requestBody;
            }
            if ((i10 & 2) != 0) {
                pushMeApiVersion = paramsForNetworkRequest.apiVersion;
            }
            return paramsForNetworkRequest.copy(jSONArray, pushMeApiVersion);
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final JSONArray getRequestBody() {
            return this.requestBody;
        }

        @NotNull
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final PushMeApiVersion getApiVersion() {
            return this.apiVersion;
        }

        @NotNull
        public final ParamsForNetworkRequest copy(@NotNull JSONArray requestBody, @NotNull PushMeApiVersion apiVersion) {
            Intrinsics.checkNotNullParameter(requestBody, "requestBody");
            Intrinsics.checkNotNullParameter(apiVersion, "apiVersion");
            return new ParamsForNetworkRequest(requestBody, apiVersion);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ParamsForNetworkRequest)) {
                return false;
            }
            ParamsForNetworkRequest paramsForNetworkRequest = (ParamsForNetworkRequest) other;
            return Intrinsics.areEqual(this.requestBody, paramsForNetworkRequest.requestBody) && this.apiVersion == paramsForNetworkRequest.apiVersion;
        }

        @NotNull
        public final PushMeApiVersion getApiVersion() {
            return this.apiVersion;
        }

        @NotNull
        public final JSONArray getRequestBody() {
            return this.requestBody;
        }

        public int hashCode() {
            return (this.requestBody.hashCode() * 31) + this.apiVersion.hashCode();
        }

        @NotNull
        public String toString() {
            return "ParamsForNetworkRequest(requestBody=" + this.requestBody + ", apiVersion=" + this.apiVersion + ")";
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes13.dex */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u001f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0006J\u0014\u0010\f\u001a\u00020\n2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\b0\u000eJ\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00060\u000eJ\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\b0\u000eR\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lru/mail/util/push/pusher/PushMeTransport$PrepareParamsResult;", "", "<init>", "()V", "successParams", "", "Lru/mail/util/push/pusher/PushMeTransport$ParamsForNetworkRequest;", "failedApps", "Lru/mail/util/push/PusherApplicationType;", "addSuccessParams", "", "params", "addFailedApplications", "apps", "", "getSuccessParams", "getFailedApps", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class PrepareParamsResult {

        @NotNull
        private final Collection<ParamsForNetworkRequest> successParams = new ArrayList();

        @NotNull
        private final Collection<PusherApplicationType> failedApps = new LinkedHashSet();

        public final void addFailedApplications(@NotNull Collection<? extends PusherApplicationType> apps) {
            Intrinsics.checkNotNullParameter(apps, "apps");
            this.failedApps.addAll(apps);
        }

        public final void addSuccessParams(@NotNull ParamsForNetworkRequest params) {
            Intrinsics.checkNotNullParameter(params, "params");
            this.successParams.add(params);
        }

        @NotNull
        public final Collection<PusherApplicationType> getFailedApps() {
            return this.failedApps;
        }

        @NotNull
        public final Collection<ParamsForNetworkRequest> getSuccessParams() {
            return this.successParams;
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes13.dex */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lru/mail/util/push/pusher/PushMeTransport$PushMeApiVersion;", "", "<init>", "(Ljava/lang/String;I)V", "V1", "V2", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private enum PushMeApiVersion {
        V1,
        V2;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

        @NotNull
        public static EnumEntries<PushMeApiVersion> getEntries() {
            return $ENTRIES;
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes13.dex */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[PushMeApiVersion.values().length];
            try {
                iArr[PushMeApiVersion.V1.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[PushMeApiVersion.V2.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PushMeTransport(@NotNull Collection<? extends PushInfoProvider> pushInfoProviders, @NotNull Context context, @NotNull DeviceIdProvider deviceIdProvider) {
        super(pushInfoProviders, context, deviceIdProvider);
        Intrinsics.checkNotNullParameter(pushInfoProviders, "pushInfoProviders");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(deviceIdProvider, "deviceIdProvider");
        this.pusherAppNameProvider = new PushMeAppNameProvider();
        this.internalTransportV1 = new PushMeV1PusherInterfaceImpl(context);
        this.internalTransportV2 = new PushMePusherInterfaceImpl(context);
    }

    private final PushParamsPreparer createPushParamsPreparer(CapabilitiesProvider capabilitiesProvider) {
        return new PushMeParamsPreparerImpl(this, getBadgeProvider(), capabilitiesProvider, getClientInfoProvider(), getDeviceIdProvider(), getPushInfoProviders(), getAuthProvider(), getPusherAppNameProvider(), this);
    }

    private final Collection<SubscribeParams> createSubscribeParams(Collection<String> userIdentifiers, Collection<PusherApplication> apps, PushMeApiVersion pushMeApiVersion) {
        ArrayList<PusherApplication> arrayList = new ArrayList();
        for (Object obj : apps) {
            if (PushMeApiVersion.V2 == pushMeApiVersion) {
                arrayList.add(obj);
            }
        }
        if (arrayList.isEmpty()) {
            return CollectionsKt.emptyList();
        }
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList, 10));
        for (PusherApplication pusherApplication : arrayList) {
            arrayList2.add(new SubscribeParams(userIdentifiers, pusherApplication.getType(), pusherApplication.getEnabledTags()));
        }
        return arrayList2;
    }

    private final BadgeProvider getBadgeProvider() {
        return new DefaultBadgeProvider();
    }

    private final ClientInfoProvider getClientInfoProvider() {
        return new ClientInfoProviderImpl(getContext());
    }

    private final PusherInterface getTransport(PushMeApiVersion version) {
        int i10 = WhenMappings.$EnumSwitchMapping$0[version.ordinal()];
        if (i10 == 1) {
            return this.internalTransportV1;
        }
        if (i10 == 2) {
            return this.internalTransportV2;
        }
        throw new NoWhenBranchMatchedException();
    }

    private final PusherTransport.Result makeNetworkRequestsToSubscribeForPushes(Collection<ParamsForNetworkRequest> params, String userIdentifier) {
        CompositeResult compositeResult = new CompositeResult();
        for (ParamsForNetworkRequest paramsForNetworkRequest : params) {
            PusherInterface.PushResult pushResultRegisterDeviceForPushes = getTransport(paramsForNetworkRequest.getApiVersion()).registerDeviceForPushes(CollectionsKt.listOf(new PusherInterface.Account(userIdentifier)), paramsForNetworkRequest.getRequestBody());
            LOG.i("Register device request result: " + pushResultRegisterDeviceForPushes);
            compositeResult.collect(mapResult(pushResultRegisterDeviceForPushes));
        }
        return compositeResult.getFinalResult();
    }

    private final PrepareParamsResult prepareMailApp(MultiAccountSettings settings) {
        PusherApplicationType pusherApplicationType = PusherApplicationType.MAIL;
        CapabilitiesProviderFactory capabilitiesProviderFactory = CapabilitiesProviderFactory.INSTANCE;
        boolean zIsPushesForMailAppEnabled = settings.isPushesForMailAppEnabled();
        FilterAccessor filterAccessor = settings.getFilterAccessor();
        Intrinsics.checkNotNullExpressionValue(filterAccessor, "getFilterAccessor(...)");
        CapabilitiesProvider capabilitiesProviderCreateProviderForMailApp = capabilitiesProviderFactory.createProviderForMailApp(zIsPushesForMailAppEnabled, filterAccessor, settings.isImportantReminderEnabled());
        Collection<String> accounts = settings.getAccounts();
        Intrinsics.checkNotNullExpressionValue(accounts, "getAccounts(...)");
        Set<Tag> enabledTagsForMailApp = settings.getEnabledTagsForMailApp();
        Intrinsics.checkNotNullExpressionValue(enabledTagsForMailApp, "getEnabledTagsForMailApp(...)");
        SubscribeParams subscribeParams = new SubscribeParams(accounts, pusherApplicationType, enabledTagsForMailApp);
        PushParamsPreparer pushParamsPreparerCreatePushParamsPreparer = createPushParamsPreparer(capabilitiesProviderCreateProviderForMailApp);
        PrepareParamsResult prepareParamsResult = new PrepareParamsResult();
        Object objMo15887preparePushSettingsIoAF18A = pushParamsPreparerCreatePushParamsPreparer.mo15887preparePushSettingsIoAF18A(CollectionsKt.listOf(subscribeParams));
        if (Result.m13128isFailureimpl(objMo15887preparePushSettingsIoAF18A)) {
            objMo15887preparePushSettingsIoAF18A = null;
        }
        JSONArray jSONArray = (JSONArray) objMo15887preparePushSettingsIoAF18A;
        if (jSONArray != null) {
            prepareParamsResult.addSuccessParams(new ParamsForNetworkRequest(jSONArray, PushMeApiVersion.V2));
            return prepareParamsResult;
        }
        LOG.w("Could not construct params for mail app");
        prepareParamsResult.addFailedApplications(CollectionsKt.listOf(pusherApplicationType));
        return prepareParamsResult;
    }

    private final PrepareParamsResult preparePortalApps(Collection<String> userIdentifiers, CapabilitiesProvider capabilitiesProvider, Collection<PusherApplication> apps) {
        PushParamsPreparer pushParamsPreparerCreatePushParamsPreparer = createPushParamsPreparer(capabilitiesProvider);
        Collection<SubscribeParams> collectionCreateSubscribeParams = createSubscribeParams(userIdentifiers, apps, PushMeApiVersion.V1);
        Collection<SubscribeParams> collectionCreateSubscribeParams2 = createSubscribeParams(userIdentifiers, apps, PushMeApiVersion.V2);
        PrepareParamsResult prepareParamsResult = new PrepareParamsResult();
        Iterator<Collection<SubscribeParams>> it = ParamSplitter.INSTANCE.split(collectionCreateSubscribeParams, getPushInfoProviders()).iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Collection<SubscribeParams> next = it.next();
            Object objMo15887preparePushSettingsIoAF18A = pushParamsPreparerCreatePushParamsPreparer.mo15887preparePushSettingsIoAF18A(next);
            JSONArray jSONArray = (JSONArray) (Result.m13128isFailureimpl(objMo15887preparePushSettingsIoAF18A) ? null : objMo15887preparePushSettingsIoAF18A);
            if (jSONArray != null) {
                prepareParamsResult.addSuccessParams(new ParamsForNetworkRequest(jSONArray, PushMeApiVersion.V1));
            } else {
                LOG.w("Could not construct params for batch: " + next);
                Collection<SubscribeParams> collection = next;
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(collection, 10));
                Iterator<T> it2 = collection.iterator();
                while (it2.hasNext()) {
                    arrayList.add(((SubscribeParams) it2.next()).getApplication());
                }
                prepareParamsResult.addFailedApplications(arrayList);
            }
        }
        for (Collection<SubscribeParams> collection2 : ParamSplitter.INSTANCE.split(collectionCreateSubscribeParams2, getPushInfoProviders())) {
            Object objMo15887preparePushSettingsIoAF18A2 = pushParamsPreparerCreatePushParamsPreparer.mo15887preparePushSettingsIoAF18A(collection2);
            if (Result.m13128isFailureimpl(objMo15887preparePushSettingsIoAF18A2)) {
                objMo15887preparePushSettingsIoAF18A2 = null;
            }
            JSONArray jSONArray2 = (JSONArray) objMo15887preparePushSettingsIoAF18A2;
            if (jSONArray2 != null) {
                prepareParamsResult.addSuccessParams(new ParamsForNetworkRequest(jSONArray2, PushMeApiVersion.V2));
            } else {
                LOG.w("Could not construct params for batch: " + collection2);
                Collection<SubscribeParams> collection3 = collection2;
                ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(collection3, 10));
                Iterator<T> it3 = collection3.iterator();
                while (it3.hasNext()) {
                    arrayList2.add(((SubscribeParams) it3.next()).getApplication());
                }
                prepareParamsResult.addFailedApplications(arrayList2);
            }
        }
        return prepareParamsResult;
    }

    @Override // ru.mail.util.push.pusher.BasePusherTransport
    @NotNull
    protected PusherInterface getInternalTransport() {
        return this.internalTransportV2;
    }

    @Override // ru.mail.util.push.pusher.BasePusherTransport
    @NotNull
    protected PusherAppNameProvider getPusherAppNameProvider() {
        return this.pusherAppNameProvider;
    }

    @Override // ru.mail.util.push.pusher.BasePusherTransport
    @NotNull
    protected PusherTransport.Result registerMailAppForPushes(@NotNull MultiAccountSettings settings, @NotNull String userIdentifier) {
        Intrinsics.checkNotNullParameter(settings, "settings");
        Intrinsics.checkNotNullParameter(userIdentifier, "userIdentifier");
        PrepareParamsResult prepareParamsResultPrepareMailApp = prepareMailApp(settings);
        if (!prepareParamsResultPrepareMailApp.getFailedApps().isEmpty()) {
            LOG.w("Subscription for mail app has failed");
            return PusherTransport.Result.UnknownError.INSTANCE;
        }
        PusherTransport.Result resultMakeNetworkRequestsToSubscribeForPushes = makeNetworkRequestsToSubscribeForPushes(prepareParamsResultPrepareMailApp.getSuccessParams(), userIdentifier);
        LOG.i("Mail app subscription result: " + resultMakeNetworkRequestsToSubscribeForPushes);
        return resultMakeNetworkRequestsToSubscribeForPushes;
    }

    @Override // ru.mail.util.push.pusher.BasePusherTransport
    @NotNull
    protected PusherTransport.Result registerPortalAppsForPushesInternal(@NotNull Collection<String> accounts, @NotNull Collection<PusherApplication> apps, @NotNull String userIdentifier) {
        PusherTransport.Result result;
        Intrinsics.checkNotNullParameter(accounts, "accounts");
        Intrinsics.checkNotNullParameter(apps, "apps");
        Intrinsics.checkNotNullParameter(userIdentifier, "userIdentifier");
        PrepareParamsResult prepareParamsResultPreparePortalApps = preparePortalApps(accounts, CapabilitiesProviderFactory.INSTANCE.createProviderForPortalApps(), apps);
        Collection<PusherApplicationType> failedApps = prepareParamsResultPreparePortalApps.getFailedApps();
        if (failedApps.size() == apps.size()) {
            return PusherTransport.Result.UnknownError.INSTANCE;
        }
        boolean z10 = makeNetworkRequestsToSubscribeForPushes(prepareParamsResultPreparePortalApps.getSuccessParams(), userIdentifier) instanceof PusherTransport.Result.OK;
        if (z10 && failedApps.isEmpty()) {
            result = PusherTransport.Result.OK.INSTANCE;
        } else if (z10) {
            LOG.w("Network result is OK but we have failed to prepared applications");
            result = PusherTransport.Result.UnknownError.INSTANCE;
        } else {
            result = PusherTransport.Result.UnknownError.INSTANCE;
        }
        LOG.i("Subscribe apps result: " + result);
        return result;
    }
}
