package com.vk.stat.sak.scheme;

import com.android.billingclient.api.BillingFlowParams;
import com.google.android.gms.analytics.ecommerce.Promotion;
import com.google.android.gms.stats.CodePackage;
import com.google.firebase.iid.GmsRpc;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.annotations.SerializedName;
import com.huawei.hms.framework.network.grs.GrsBaseInfo;
import com.vk.api.sdk.exceptions.VKApiCodes;
import com.vk.push.core.analytics.AnalyticsBaseParamsConstantsKt;
import com.vk.push.pushsdk.utils.JsonMessageParser;
import com.vk.pushme.analytcis.AnalyticsErrorType;
import com.vk.superapp.api.analytics.RegistrationStatParamsFactory;
import com.vk.superapp.api.dto.auth.PasskeyBeginResult;
import com.vk.usersstore.blockstore.deletereceiver.BlockstoreDeleteReceiver;
import java.lang.reflect.Type;
import java.util.List;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.api.MailContract;
import ru.mail.authorizationsdk.feature.beforerecovery.presentation.BeforeRecoveryVKIDViewModel;
import ru.mail.bonus.BonusConstants;
import ru.mail.cloud.upload.internal.analytics.EventParams;
import ru.mail.kotlett.spec.DivActionSpec;
import ru.mail.portal.apps.shared.presentationlayer.handlers.FileTypes;
import ru.mail.registration.Statistic;
import ru.mail.remotelayout.data.dto.button.ButtonDto;
import ru.mail.ui.promosheet.npc.NpcPromoSheetProvider;
import ru.mail.util.push.NotificationUpdater;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes20.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0015\bf\u0018\u00002\u00020\u0001:\u0014\u0002\u0003\u0004\u0005\u0006\u0007\b\t\n\u000b\f\r\u000e\u000f\u0010\u0011\u0012\u0013\u0014\u0015¨\u0006\u0016"}, d2 = {"Lcom/vk/stat/sak/scheme/SchemeStatSak;", "", "MultiaccountFieldItem", "TypeVkPayCheckoutItem", "TypeRegistrationItem", "TypeAction", "BaseOkResponse", "RegistrationFieldItem", "SakSessionsEventFieldItem", "EventProductMain", "TypeErrorShownItem", "NavigationFieldItem", "TypeDebugStatsItem", "EcosystemNavigationItem", "EventScreen", "TypeVkidEcosystemNavigationItem", "TypeVkConnectNavigationItem", "TypeSakSessionsEventItem", "TypeMultiaccountsItem", "ErrorView", "EcosystemNavigationOptionItem", "NavigationPayload", "sak_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface SchemeStatSak {

    /* JADX WARN: Enum visitor error
    jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v1 com.vk.stat.sak.scheme.SchemeStatSak$BaseOkResponse[], still in use, count: 1, list:
      (r0v1 com.vk.stat.sak.scheme.SchemeStatSak$BaseOkResponse[]) from 0x000d: INVOKE (r0v1 com.vk.stat.sak.scheme.SchemeStatSak$BaseOkResponse[]) STATIC call: kotlin.enums.EnumEntriesKt.enumEntries(java.lang.Enum[]):kotlin.enums.EnumEntries A[MD:<E extends java.lang.Enum<E>>:(E extends java.lang.Enum<E>[]):kotlin.enums.EnumEntries<E extends java.lang.Enum<E>> (m), WRAPPED] (LINE:14)
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
    /* JADX INFO: loaded from: classes12.dex */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0003\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0002j\u0002\b\u0003¨\u0006\u0004"}, d2 = {"Lcom/vk/stat/sak/scheme/SchemeStatSak$BaseOkResponse;", "", "Serializer", "OK", "sak_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class BaseOkResponse {
        OK;

        private static final /* synthetic */ EnumEntries kastatsbilkvmocb;

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J&\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u00022\b\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016¨\u0006\f"}, d2 = {"Lcom/vk/stat/sak/scheme/SchemeStatSak$BaseOkResponse$Serializer;", "Lcom/google/gson/JsonSerializer;", "Lcom/vk/stat/sak/scheme/SchemeStatSak$BaseOkResponse;", "<init>", "()V", "serialize", "Lcom/google/gson/JsonElement;", "src", "typeOfSrc", "Ljava/lang/reflect/Type;", "context", "Lcom/google/gson/JsonSerializationContext;", "sak_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
        @SourceDebugExtension({"SMAP\nSchemeStatSak.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SchemeStatSak.kt\ncom/vk/stat/sak/scheme/SchemeStatSak$BaseOkResponse$Serializer\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,3265:1\n1#2:3266\n*E\n"})
        public static final class Serializer implements JsonSerializer<BaseOkResponse> {
            @Override // com.google.gson.JsonSerializer
            @NotNull
            public JsonElement serialize(@Nullable BaseOkResponse src, @Nullable Type typeOfSrc, @Nullable JsonSerializationContext context) {
                if (src != null) {
                    return new JsonPrimitive(Integer.valueOf(BaseOkResponse.access$getValue$p(src)));
                }
                JsonNull INSTANCE = JsonNull.INSTANCE;
                Intrinsics.checkNotNullExpressionValue(INSTANCE, "INSTANCE");
                return INSTANCE;
            }
        }

        static {
            kastatsbilkvmocb = EnumEntriesKt.enumEntries(baseOkResponseArr);
        }

        private BaseOkResponse() {
            super("OK", 0);
        }

        public static final /* synthetic */ int access$getValue$p(BaseOkResponse baseOkResponse) {
            baseOkResponse.getClass();
            return 1;
        }

        @NotNull
        public static EnumEntries<BaseOkResponse> getEntries() {
            return kastatsbilkvmocb;
        }

        public static BaseOkResponse valueOf(String str) {
            return (BaseOkResponse) Enum.valueOf(BaseOkResponse.class, str);
        }

        public static BaseOkResponse[] values() {
            return (BaseOkResponse[]) kastatsbilkvmoca.clone();
        }
    }

    /* JADX WARN: Enum visitor error
    jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v1 com.vk.stat.sak.scheme.SchemeStatSak$EcosystemNavigationItem[], still in use, count: 1, list:
      (r0v1 com.vk.stat.sak.scheme.SchemeStatSak$EcosystemNavigationItem[]) from 0x0077: INVOKE (r0v1 com.vk.stat.sak.scheme.SchemeStatSak$EcosystemNavigationItem[]) STATIC call: kotlin.enums.EnumEntriesKt.enumEntries(java.lang.Enum[]):kotlin.enums.EnumEntries A[MD:<E extends java.lang.Enum<E>>:(E extends java.lang.Enum<E>[]):kotlin.enums.EnumEntries<E extends java.lang.Enum<E>> (m), WRAPPED] (LINE:120)
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
    /* JADX INFO: loaded from: classes12.dex */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\f\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001j\u0002\b\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\f¨\u0006\r"}, d2 = {"Lcom/vk/stat/sak/scheme/SchemeStatSak$EcosystemNavigationItem;", "", "EMAIL", "CLOUD", "AVATAR", "VKID_LK", "SUBSCRIPTIONS", "BALANCE", "SETTINGS", "THEME", "HELP", "LOGOUT", "SWITCHER", "sak_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class EcosystemNavigationItem {
        EMAIL,
        CLOUD,
        AVATAR,
        VKID_LK,
        SUBSCRIPTIONS,
        BALANCE,
        SETTINGS,
        THEME,
        HELP,
        LOGOUT,
        SWITCHER;

        private static final /* synthetic */ EnumEntries kastatsbilkvmocb;

        static {
            kastatsbilkvmocb = EnumEntriesKt.enumEntries(ecosystemNavigationItemArr);
        }

        private EcosystemNavigationItem() {
            super(str, i);
        }

        @NotNull
        public static EnumEntries<EcosystemNavigationItem> getEntries() {
            return kastatsbilkvmocb;
        }

        public static EcosystemNavigationItem valueOf(String str) {
            return (EcosystemNavigationItem) Enum.valueOf(EcosystemNavigationItem.class, str);
        }

        public static EcosystemNavigationItem[] values() {
            return (EcosystemNavigationItem[]) kastatsbilkvmoca.clone();
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes12.dex */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\n\b\u0086\b\u0018\u00002\u00020\u0001:\u0001\u001eB\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\b\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\n\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b\n\u0010\u000bJ$\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u0004HÆ\u0001¢\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u000f\u001a\u00020\u000eHÖ\u0001¢\u0006\u0004\b\u000f\u0010\u0010J\u0010\u0010\u0012\u001a\u00020\u0011HÖ\u0001¢\u0006\u0004\b\u0012\u0010\u0013J\u001a\u0010\u0016\u001a\u00020\u00152\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0003\u001a\u00020\u00028\u0006X\u0087\u0004¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\tR\u001a\u0010\u0005\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\f\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\u000b¨\u0006\u001f"}, d2 = {"Lcom/vk/stat/sak/scheme/SchemeStatSak$EcosystemNavigationOptionItem;", "", "Lcom/vk/stat/sak/scheme/SchemeStatSak$EcosystemNavigationItem;", "names", "Lcom/vk/stat/sak/scheme/SchemeStatSak$EcosystemNavigationOptionItem$Values;", "values", "<init>", "(Lcom/vk/stat/sak/scheme/SchemeStatSak$EcosystemNavigationItem;Lcom/vk/stat/sak/scheme/SchemeStatSak$EcosystemNavigationOptionItem$Values;)V", "component1", "()Lcom/vk/stat/sak/scheme/SchemeStatSak$EcosystemNavigationItem;", "component2", "()Lcom/vk/stat/sak/scheme/SchemeStatSak$EcosystemNavigationOptionItem$Values;", "copy", "(Lcom/vk/stat/sak/scheme/SchemeStatSak$EcosystemNavigationItem;Lcom/vk/stat/sak/scheme/SchemeStatSak$EcosystemNavigationOptionItem$Values;)Lcom/vk/stat/sak/scheme/SchemeStatSak$EcosystemNavigationOptionItem;", "", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "kastatsbilkvmoca", "Lcom/vk/stat/sak/scheme/SchemeStatSak$EcosystemNavigationItem;", "getNames", "kastatsbilkvmocb", "Lcom/vk/stat/sak/scheme/SchemeStatSak$EcosystemNavigationOptionItem$Values;", "getValues", "Values", "sak_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final /* data */ class EcosystemNavigationOptionItem {

        /* JADX INFO: renamed from: kastatsbilkvmoca, reason: from kotlin metadata */
        @SerializedName("names")
        @NotNull
        private final EcosystemNavigationItem names;

        /* JADX INFO: renamed from: kastatsbilkvmocb, reason: from kotlin metadata */
        @SerializedName("values")
        @NotNull
        private final Values values;

        /* JADX WARN: Enum visitor error
        jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v1 com.vk.stat.sak.scheme.SchemeStatSak$EcosystemNavigationOptionItem$Values[], still in use, count: 1, list:
          (r0v1 com.vk.stat.sak.scheme.SchemeStatSak$EcosystemNavigationOptionItem$Values[]) from 0x002e: INVOKE (r0v1 com.vk.stat.sak.scheme.SchemeStatSak$EcosystemNavigationOptionItem$Values[]) STATIC call: kotlin.enums.EnumEntriesKt.enumEntries(java.lang.Enum[]):kotlin.enums.EnumEntries A[MD:<E extends java.lang.Enum<E>>:(E extends java.lang.Enum<E>[]):kotlin.enums.EnumEntries<E extends java.lang.Enum<E>> (m), WRAPPED] (LINE:47)
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
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001j\u0002\b\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/vk/stat/sak/scheme/SchemeStatSak$EcosystemNavigationOptionItem$Values;", "", "ECOSYSTEM", "VK", "SERVICE", "MULTIACCOUNT", "sak_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
        public static final class Values {
            ECOSYSTEM,
            VK,
            SERVICE,
            MULTIACCOUNT;

            private static final /* synthetic */ EnumEntries kastatsbilkvmocb;

            static {
                kastatsbilkvmocb = EnumEntriesKt.enumEntries(valuesArr);
            }

            private Values() {
                super(str, i);
            }

            @NotNull
            public static EnumEntries<Values> getEntries() {
                return kastatsbilkvmocb;
            }

            public static Values valueOf(String str) {
                return (Values) Enum.valueOf(Values.class, str);
            }

            public static Values[] values() {
                return (Values[]) kastatsbilkvmoca.clone();
            }
        }

        public EcosystemNavigationOptionItem(@NotNull EcosystemNavigationItem names, @NotNull Values values) {
            Intrinsics.checkNotNullParameter(names, "names");
            Intrinsics.checkNotNullParameter(values, "values");
            this.names = names;
            this.values = values;
        }

        public static /* synthetic */ EcosystemNavigationOptionItem copy$default(EcosystemNavigationOptionItem ecosystemNavigationOptionItem, EcosystemNavigationItem ecosystemNavigationItem, Values values, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                ecosystemNavigationItem = ecosystemNavigationOptionItem.names;
            }
            if ((i10 & 2) != 0) {
                values = ecosystemNavigationOptionItem.values;
            }
            return ecosystemNavigationOptionItem.copy(ecosystemNavigationItem, values);
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final EcosystemNavigationItem getNames() {
            return this.names;
        }

        @NotNull
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final Values getValues() {
            return this.values;
        }

        @NotNull
        public final EcosystemNavigationOptionItem copy(@NotNull EcosystemNavigationItem names, @NotNull Values values) {
            Intrinsics.checkNotNullParameter(names, "names");
            Intrinsics.checkNotNullParameter(values, "values");
            return new EcosystemNavigationOptionItem(names, values);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof EcosystemNavigationOptionItem)) {
                return false;
            }
            EcosystemNavigationOptionItem ecosystemNavigationOptionItem = (EcosystemNavigationOptionItem) other;
            return this.names == ecosystemNavigationOptionItem.names && this.values == ecosystemNavigationOptionItem.values;
        }

        @NotNull
        public final EcosystemNavigationItem getNames() {
            return this.names;
        }

        @NotNull
        public final Values getValues() {
            return this.values;
        }

        public int hashCode() {
            return this.values.hashCode() + (this.names.hashCode() * 31);
        }

        @NotNull
        public String toString() {
            return "EcosystemNavigationOptionItem(names=" + this.names + ", values=" + this.values + ')';
        }
    }

    /* JADX WARN: Enum visitor error
    jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v1 com.vk.stat.sak.scheme.SchemeStatSak$ErrorView[], still in use, count: 1, list:
      (r0v1 com.vk.stat.sak.scheme.SchemeStatSak$ErrorView[]) from 0x002e: INVOKE (r0v1 com.vk.stat.sak.scheme.SchemeStatSak$ErrorView[]) STATIC call: kotlin.enums.EnumEntriesKt.enumEntries(java.lang.Enum[]):kotlin.enums.EnumEntries A[MD:<E extends java.lang.Enum<E>>:(E extends java.lang.Enum<E>[]):kotlin.enums.EnumEntries<E extends java.lang.Enum<E>> (m), WRAPPED] (LINE:47)
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
    /* JADX INFO: loaded from: classes12.dex */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001j\u0002\b\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/vk/stat/sak/scheme/SchemeStatSak$ErrorView;", "", "INPUT", "ALERT", "FULLSCREEN", "MODALCARD", "sak_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class ErrorView {
        INPUT,
        ALERT,
        FULLSCREEN,
        MODALCARD;

        private static final /* synthetic */ EnumEntries kastatsbilkvmocb;

        static {
            kastatsbilkvmocb = EnumEntriesKt.enumEntries(errorViewArr);
        }

        private ErrorView() {
            super(str, i);
        }

        @NotNull
        public static EnumEntries<ErrorView> getEntries() {
            return kastatsbilkvmocb;
        }

        public static ErrorView valueOf(String str) {
            return (ErrorView) Enum.valueOf(ErrorView.class, str);
        }

        public static ErrorView[] values() {
            return (ErrorView[]) kastatsbilkvmoca.clone();
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes12.dex */
    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0019\b\u0086\b\u0018\u0000 52\u00020\u0001:\u0003567J\u0010\u0010\u0003\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0006\u001a\u00020\u0005HÆ\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\t\u001a\u00020\bHÆ\u0003¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u000b\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u000b\u0010\u0004J\u0010\u0010\f\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\f\u0010\u0004J\u0010\u0010\u000e\u001a\u00020\rHÆ\u0003¢\u0006\u0004\b\u000e\u0010\u000fJ\u0012\u0010\u0011\u001a\u0004\u0018\u00010\u0010HÆ\u0003¢\u0006\u0004\b\u0011\u0010\u0012JX\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0013\u001a\u00020\u00022\b\b\u0002\u0010\u0014\u001a\u00020\u00052\b\b\u0002\u0010\u0015\u001a\u00020\b2\b\b\u0002\u0010\u0016\u001a\u00020\u00022\b\b\u0002\u0010\u0017\u001a\u00020\u00022\b\b\u0002\u0010\u0018\u001a\u00020\r2\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0010HÆ\u0001¢\u0006\u0004\b\u001a\u0010\u001bJ\u0010\u0010\u001c\u001a\u00020\u0005HÖ\u0001¢\u0006\u0004\b\u001c\u0010\u0007J\u0010\u0010\u001d\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u001d\u0010\u0004J\u001a\u0010 \u001a\u00020\u001f2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b \u0010!R\u001a\u0010\u0013\u001a\u00020\u00028\u0006X\u0087\u0004¢\u0006\f\n\u0004\b\"\u0010#\u001a\u0004\b$\u0010\u0004R\u001a\u0010\u0014\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\f\n\u0004\b%\u0010&\u001a\u0004\b'\u0010\u0007R\u001a\u0010\u0015\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\f\n\u0004\b(\u0010)\u001a\u0004\b*\u0010\nR\u001a\u0010\u0016\u001a\u00020\u00028\u0006X\u0087\u0004¢\u0006\f\n\u0004\b+\u0010#\u001a\u0004\b,\u0010\u0004R\u001a\u0010\u0017\u001a\u00020\u00028\u0006X\u0087\u0004¢\u0006\f\n\u0004\b-\u0010#\u001a\u0004\b.\u0010\u0004R\u001a\u0010\u0018\u001a\u00020\r8\u0006X\u0087\u0004¢\u0006\f\n\u0004\b/\u00100\u001a\u0004\b1\u0010\u000fR\u001c\u0010\u0019\u001a\u0004\u0018\u00010\u00108\u0006X\u0087\u0004¢\u0006\f\n\u0004\b2\u00103\u001a\u0004\b4\u0010\u0012¨\u00068"}, d2 = {"Lcom/vk/stat/sak/scheme/SchemeStatSak$EventProductMain;", "", "", "component1", "()I", "", "component2", "()Ljava/lang/String;", "Lcom/vk/stat/sak/scheme/SchemeStatSak$EventScreen;", "component3", "()Lcom/vk/stat/sak/scheme/SchemeStatSak$EventScreen;", "component4", "component5", "Lcom/vk/stat/sak/scheme/SchemeStatSak$EventProductMain$Type;", "component6", "()Lcom/vk/stat/sak/scheme/SchemeStatSak$EventProductMain$Type;", "Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeAction;", "component7", "()Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeAction;", "id", "timestamp", "screen", "prevEventId", "prevNavId", "type", "typeAction", "copy", "(ILjava/lang/String;Lcom/vk/stat/sak/scheme/SchemeStatSak$EventScreen;IILcom/vk/stat/sak/scheme/SchemeStatSak$EventProductMain$Type;Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeAction;)Lcom/vk/stat/sak/scheme/SchemeStatSak$EventProductMain;", "toString", "hashCode", "other", "", "equals", "(Ljava/lang/Object;)Z", "kastatsbilkvmoca", "I", "getId", "kastatsbilkvmocb", "Ljava/lang/String;", "getTimestamp", "kastatsbilkvmocc", "Lcom/vk/stat/sak/scheme/SchemeStatSak$EventScreen;", "getScreen", "kastatsbilkvmocd", "getPrevEventId", "kastatsbilkvmoce", "getPrevNavId", "kastatsbilkvmocf", "Lcom/vk/stat/sak/scheme/SchemeStatSak$EventProductMain$Type;", "getType", "kastatsbilkvmocg", "Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeAction;", "getTypeAction", "Companion", "Type", "Payload", "sak_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final /* data */ class EventProductMain {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        @NotNull
        public static final Companion INSTANCE = new Companion(null);

        /* JADX INFO: renamed from: kastatsbilkvmoca, reason: from kotlin metadata */
        @SerializedName("id")
        private final int id;

        /* JADX INFO: renamed from: kastatsbilkvmocb, reason: from kotlin metadata */
        @SerializedName("timestamp")
        @NotNull
        private final String timestamp;

        /* JADX INFO: renamed from: kastatsbilkvmocc, reason: from kotlin metadata */
        @SerializedName("screen")
        @NotNull
        private final EventScreen screen;

        /* JADX INFO: renamed from: kastatsbilkvmocd, reason: from kotlin metadata */
        @SerializedName("prev_event_id")
        private final int prevEventId;

        /* JADX INFO: renamed from: kastatsbilkvmoce, reason: from kotlin metadata */
        @SerializedName("prev_nav_id")
        private final int prevNavId;

        /* JADX INFO: renamed from: kastatsbilkvmocf, reason: from kotlin metadata */
        @SerializedName("type")
        @NotNull
        private final Type type;

        /* JADX INFO: renamed from: kastatsbilkvmocg, reason: from kotlin metadata */
        @SerializedName("type_action")
        @Nullable
        private final TypeAction typeAction;

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J6\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u000f¨\u0006\u0010"}, d2 = {"Lcom/vk/stat/sak/scheme/SchemeStatSak$EventProductMain$Companion;", "", "<init>", "()V", "create", "Lcom/vk/stat/sak/scheme/SchemeStatSak$EventProductMain;", "id", "", "timestamp", "", "screen", "Lcom/vk/stat/sak/scheme/SchemeStatSak$EventScreen;", "prevEventId", "prevNavId", JsonMessageParser.Keys.PAYLOAD_JSON_KEY, "Lcom/vk/stat/sak/scheme/SchemeStatSak$EventProductMain$Payload;", "sak_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            @NotNull
            public final EventProductMain create(int id2, @NotNull String timestamp, @NotNull EventScreen screen, int prevEventId, int prevNavId, @NotNull Payload payload) {
                Intrinsics.checkNotNullParameter(timestamp, "timestamp");
                Intrinsics.checkNotNullParameter(screen, "screen");
                Intrinsics.checkNotNullParameter(payload, "payload");
                if (payload instanceof TypeAction) {
                    return new EventProductMain(id2, timestamp, screen, prevEventId, prevNavId, Type.TYPE_ACTION, (TypeAction) payload, null);
                }
                throw new IllegalArgumentException("payload must be one of(TypeAction)");
            }

            private Companion() {
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\bf\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lcom/vk/stat/sak/scheme/SchemeStatSak$EventProductMain$Payload;", "", "sak_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
        public interface Payload {
        }

        /* JADX WARN: Enum visitor error
        jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v1 com.vk.stat.sak.scheme.SchemeStatSak$EventProductMain$Type[], still in use, count: 1, list:
          (r0v1 com.vk.stat.sak.scheme.SchemeStatSak$EventProductMain$Type[]) from 0x000d: INVOKE (r0v1 com.vk.stat.sak.scheme.SchemeStatSak$EventProductMain$Type[]) STATIC call: kotlin.enums.EnumEntriesKt.enumEntries(java.lang.Enum[]):kotlin.enums.EnumEntries A[MD:<E extends java.lang.Enum<E>>:(E extends java.lang.Enum<E>[]):kotlin.enums.EnumEntries<E extends java.lang.Enum<E>> (m), WRAPPED] (LINE:14)
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
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0002\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001j\u0002\b\u0002¨\u0006\u0003"}, d2 = {"Lcom/vk/stat/sak/scheme/SchemeStatSak$EventProductMain$Type;", "", "TYPE_ACTION", "sak_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
        public static final class Type {
            TYPE_ACTION;

            private static final /* synthetic */ EnumEntries kastatsbilkvmocb;

            static {
                kastatsbilkvmocb = EnumEntriesKt.enumEntries(typeArr);
            }

            private Type() {
                super("TYPE_ACTION", 0);
            }

            @NotNull
            public static EnumEntries<Type> getEntries() {
                return kastatsbilkvmocb;
            }

            public static Type valueOf(String str) {
                return (Type) Enum.valueOf(Type.class, str);
            }

            public static Type[] values() {
                return (Type[]) kastatsbilkvmoca.clone();
            }
        }

        public /* synthetic */ EventProductMain(int i10, String str, EventScreen eventScreen, int i11, int i12, Type type, TypeAction typeAction, DefaultConstructorMarker defaultConstructorMarker) {
            this(i10, str, eventScreen, i11, i12, type, typeAction);
        }

        public static /* synthetic */ EventProductMain copy$default(EventProductMain eventProductMain, int i10, String str, EventScreen eventScreen, int i11, int i12, Type type, TypeAction typeAction, int i13, Object obj) {
            if ((i13 & 1) != 0) {
                i10 = eventProductMain.id;
            }
            if ((i13 & 2) != 0) {
                str = eventProductMain.timestamp;
            }
            if ((i13 & 4) != 0) {
                eventScreen = eventProductMain.screen;
            }
            if ((i13 & 8) != 0) {
                i11 = eventProductMain.prevEventId;
            }
            if ((i13 & 16) != 0) {
                i12 = eventProductMain.prevNavId;
            }
            if ((i13 & 32) != 0) {
                type = eventProductMain.type;
            }
            if ((i13 & 64) != 0) {
                typeAction = eventProductMain.typeAction;
            }
            Type type2 = type;
            TypeAction typeAction2 = typeAction;
            int i14 = i12;
            EventScreen eventScreen2 = eventScreen;
            return eventProductMain.copy(i10, str, eventScreen2, i11, i14, type2, typeAction2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getId() {
            return this.id;
        }

        @NotNull
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getTimestamp() {
            return this.timestamp;
        }

        @NotNull
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final EventScreen getScreen() {
            return this.screen;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final int getPrevEventId() {
            return this.prevEventId;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final int getPrevNavId() {
            return this.prevNavId;
        }

        @NotNull
        /* JADX INFO: renamed from: component6, reason: from getter */
        public final Type getType() {
            return this.type;
        }

        @Nullable
        /* JADX INFO: renamed from: component7, reason: from getter */
        public final TypeAction getTypeAction() {
            return this.typeAction;
        }

        @NotNull
        public final EventProductMain copy(int id2, @NotNull String timestamp, @NotNull EventScreen screen, int prevEventId, int prevNavId, @NotNull Type type, @Nullable TypeAction typeAction) {
            Intrinsics.checkNotNullParameter(timestamp, "timestamp");
            Intrinsics.checkNotNullParameter(screen, "screen");
            Intrinsics.checkNotNullParameter(type, "type");
            return new EventProductMain(id2, timestamp, screen, prevEventId, prevNavId, type, typeAction);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof EventProductMain)) {
                return false;
            }
            EventProductMain eventProductMain = (EventProductMain) other;
            return this.id == eventProductMain.id && Intrinsics.areEqual(this.timestamp, eventProductMain.timestamp) && this.screen == eventProductMain.screen && this.prevEventId == eventProductMain.prevEventId && this.prevNavId == eventProductMain.prevNavId && this.type == eventProductMain.type && Intrinsics.areEqual(this.typeAction, eventProductMain.typeAction);
        }

        public final int getId() {
            return this.id;
        }

        public final int getPrevEventId() {
            return this.prevEventId;
        }

        public final int getPrevNavId() {
            return this.prevNavId;
        }

        @NotNull
        public final EventScreen getScreen() {
            return this.screen;
        }

        @NotNull
        public final String getTimestamp() {
            return this.timestamp;
        }

        @NotNull
        public final Type getType() {
            return this.type;
        }

        @Nullable
        public final TypeAction getTypeAction() {
            return this.typeAction;
        }

        public int hashCode() {
            int iHashCode = (this.type.hashCode() + ((Integer.hashCode(this.prevNavId) + ((Integer.hashCode(this.prevEventId) + ((this.screen.hashCode() + ((this.timestamp.hashCode() + (Integer.hashCode(this.id) * 31)) * 31)) * 31)) * 31)) * 31)) * 31;
            TypeAction typeAction = this.typeAction;
            return iHashCode + (typeAction == null ? 0 : typeAction.hashCode());
        }

        @NotNull
        public String toString() {
            return "EventProductMain(id=" + this.id + ", timestamp=" + this.timestamp + ", screen=" + this.screen + ", prevEventId=" + this.prevEventId + ", prevNavId=" + this.prevNavId + ", type=" + this.type + ", typeAction=" + this.typeAction + ')';
        }

        private EventProductMain(int i10, String str, EventScreen eventScreen, int i11, int i12, Type type, TypeAction typeAction) {
            this.id = i10;
            this.timestamp = str;
            this.screen = eventScreen;
            this.prevEventId = i11;
            this.prevNavId = i12;
            this.type = type;
            this.typeAction = typeAction;
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes12.dex */
    @Metadata(d1 = {"\u0000\r\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0003\bí\u0001\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001dj\u0002\b\u001ej\u0002\b\u001fj\u0002\b j\u0002\b!j\u0002\b\"j\u0002\b#j\u0002\b$j\u0002\b%j\u0002\b&j\u0002\b'j\u0002\b(j\u0002\b)j\u0002\b*j\u0002\b+j\u0002\b,j\u0002\b-j\u0002\b.j\u0002\b/j\u0002\b0j\u0002\b1j\u0002\b2j\u0002\b3j\u0002\b4j\u0002\b5j\u0002\b6j\u0002\b7j\u0002\b8j\u0002\b9j\u0002\b:j\u0002\b;j\u0002\b<j\u0002\b=j\u0002\b>j\u0002\b?j\u0002\b@j\u0002\bAj\u0002\bBj\u0002\bCj\u0002\bDj\u0002\bEj\u0002\bFj\u0002\bGj\u0002\bHj\u0002\bIj\u0002\bJj\u0002\bKj\u0002\bLj\u0002\bMj\u0002\bNj\u0002\bOj\u0002\bPj\u0002\bQj\u0002\bRj\u0002\bSj\u0002\bTj\u0002\bUj\u0002\bVj\u0002\bWj\u0002\bXj\u0002\bYj\u0002\bZj\u0002\b[j\u0002\b\\j\u0002\b]j\u0002\b^j\u0002\b_j\u0002\b`j\u0002\baj\u0002\bbj\u0002\bcj\u0002\bdj\u0002\bej\u0002\bfj\u0002\bgj\u0002\bhj\u0002\bij\u0002\bjj\u0002\bkj\u0002\blj\u0002\bmj\u0002\bnj\u0002\boj\u0002\bpj\u0002\bqj\u0002\brj\u0002\bsj\u0002\btj\u0002\buj\u0002\bvj\u0002\bwj\u0002\bxj\u0002\byj\u0002\bzj\u0002\b{j\u0002\b|j\u0002\b}j\u0002\b~j\u0002\b\u007fj\u0003\b\u0080\u0001j\u0003\b\u0081\u0001j\u0003\b\u0082\u0001j\u0003\b\u0083\u0001j\u0003\b\u0084\u0001j\u0003\b\u0085\u0001j\u0003\b\u0086\u0001j\u0003\b\u0087\u0001j\u0003\b\u0088\u0001j\u0003\b\u0089\u0001j\u0003\b\u008a\u0001j\u0003\b\u008b\u0001j\u0003\b\u008c\u0001j\u0003\b\u008d\u0001j\u0003\b\u008e\u0001j\u0003\b\u008f\u0001j\u0003\b\u0090\u0001j\u0003\b\u0091\u0001j\u0003\b\u0092\u0001j\u0003\b\u0093\u0001j\u0003\b\u0094\u0001j\u0003\b\u0095\u0001j\u0003\b\u0096\u0001j\u0003\b\u0097\u0001j\u0003\b\u0098\u0001j\u0003\b\u0099\u0001j\u0003\b\u009a\u0001j\u0003\b\u009b\u0001j\u0003\b\u009c\u0001j\u0003\b\u009d\u0001j\u0003\b\u009e\u0001j\u0003\b\u009f\u0001j\u0003\b \u0001j\u0003\b¡\u0001j\u0003\b¢\u0001j\u0003\b£\u0001j\u0003\b¤\u0001j\u0003\b¥\u0001j\u0003\b¦\u0001j\u0003\b§\u0001j\u0003\b¨\u0001j\u0003\b©\u0001j\u0003\bª\u0001j\u0003\b«\u0001j\u0003\b¬\u0001j\u0003\b\u00ad\u0001j\u0003\b®\u0001j\u0003\b¯\u0001j\u0003\b°\u0001j\u0003\b±\u0001j\u0003\b²\u0001j\u0003\b³\u0001j\u0003\b´\u0001j\u0003\bµ\u0001j\u0003\b¶\u0001j\u0003\b·\u0001j\u0003\b¸\u0001j\u0003\b¹\u0001j\u0003\bº\u0001j\u0003\b»\u0001j\u0003\b¼\u0001j\u0003\b½\u0001j\u0003\b¾\u0001j\u0003\b¿\u0001j\u0003\bÀ\u0001j\u0003\bÁ\u0001j\u0003\bÂ\u0001j\u0003\bÃ\u0001j\u0003\bÄ\u0001j\u0003\bÅ\u0001j\u0003\bÆ\u0001j\u0003\bÇ\u0001j\u0003\bÈ\u0001j\u0003\bÉ\u0001j\u0003\bÊ\u0001j\u0003\bË\u0001j\u0003\bÌ\u0001j\u0003\bÍ\u0001j\u0003\bÎ\u0001j\u0003\bÏ\u0001j\u0003\bÐ\u0001j\u0003\bÑ\u0001j\u0003\bÒ\u0001j\u0003\bÓ\u0001j\u0003\bÔ\u0001j\u0003\bÕ\u0001j\u0003\bÖ\u0001j\u0003\b×\u0001j\u0003\bØ\u0001j\u0003\bÙ\u0001j\u0003\bÚ\u0001j\u0003\bÛ\u0001j\u0003\bÜ\u0001j\u0003\bÝ\u0001j\u0003\bÞ\u0001j\u0003\bß\u0001j\u0003\bà\u0001j\u0003\bá\u0001j\u0003\bâ\u0001j\u0003\bã\u0001j\u0003\bä\u0001j\u0003\bå\u0001j\u0003\bæ\u0001j\u0003\bç\u0001j\u0003\bè\u0001j\u0003\bé\u0001j\u0003\bê\u0001j\u0003\bë\u0001j\u0003\bì\u0001j\u0003\bí\u0001¨\u0006î\u0001"}, d2 = {"Lcom/vk/stat/sak/scheme/SchemeStatSak$EventScreen;", "", "Serializer", "ACCOUNT_CONFIRM_PASSWORD", "ACCOUNT_CONFIRM_VERIFY", "AUTH_QR_CODE", "BANNED_ACCOUNT", "CAPTCHA", "CONTACTS_APPS_ADD_PHONE", "CONTACTS_APPS_ADD_EMAIL", "CONTACTS_APPS_ADD_ADDRESS", "CONTACTS_APPS_EDIT_PHONE", "CONTACTS_APPS_EDIT_EMAIL", "CONTACTS_APPS_EDIT_ADDRESS", "CONSENT_SCREEN", "NOWHERE_DIALOG", "FAST_SILENT_AUTH_EXISTING_ACCOUNT", "FAST_SILENT_AUTH_AS_USER", "FAST_SILENT_AUTH_DOWNLOAD", "FAST_SILENT_AUTH_SUCCESS", "FAST_SILENT_AUTH_ERROR", "GAME", "MINI_APP", "NOWHERE", "PASSPORT_RESTORE", "REGISTRATION_PHONE", "PROMO_MAX", "REGISTRATION_PERMISSION", "REGISTRATION_EXISTENT_ACCOUNT_NO_PASSWORD_OK", "REGISTRATION_EXISTENT_ACCOUNT_PASSWORDLESS", "REGISTRATION_CONNECT_GMAIL", "REGISTRATION_PHONE_VERIFY", "REGISTRATION_PHONE_VERIFY_LIB", "REGISTRATION_NAME", "REGISTRATION_NAME_ADD", "REGISTRATION_INFO_ABOUT_YOURSELF", "REGISTRATION_INFO_ABOUT_YOURSELF_ADD", "REGISTRATION_EXISTENT_ACCOUNT", "REGISTRATION_EXISTENT_ACCOUNT_NO_PASSWORD", "REGISTRATION_BDAY", "REGISTRATION_BDAY_ADD", "REGISTRATION_PASSWORD", "REGISTRATION_PASSWORD_ADD", "REGISTRATION_IMPORT_CONTACTS", "REGISTRATION_CONNECT_FACEBOOK", "REGISTRATION_CONNECT_OK", "REGISTRATION_CONNECT_TWITTER", "REGISTRATION_PHOTO", "REGISTRATION_CHOOSE_PHOTO", "REGISTRATION_TAKE_PHOTO", "REGISTRATION_STYLE_PHOTO", "REGISTRATION_CROP_PHOTO", "REGISTRATION_LIST_ADDRESS_BOOK", "REGISTRATION_LIST_FRIENDS_FACEBOOK", "REGISTRATION_LIST_FRIENDS_OK", "REGISTRATION_LIST_FRIENDS_TWITTER", "REGISTRATION_LIST_CONTACTS_GMAIL", "REGISTRATION_PUSH", "REGISTRATION_GEO", "REGISTRATION_PUSH_REQUEST", "REGISTRATION_SUBJECTS", "REGISTRATION_EMAIL_VERIFY", "REGISTRATION_EMAIL_PASSWORD", "REGISTRATION_EMAIL", "REGISTRATION_EMAIL_EXPLANATION", "REGISTRATION_NEW_ACCOUNT", "REGISTRATION_EXISTENT_ACCOUNT_RESTORE", "LK_PASSWORD", "RESTORE_ACCOUNT", "HAVE_ACCOUNT_QUESTION", "HAVE_ACCOUNT_CREDENTIALS", "HAVE_ACCOUNT_SUPPORT", "CONTACTING_SUPPORT", "VERIFICATION_ASK_NUMBER", "VERIFICATION_ENTER_NUMBER", "VERIFICATION_PHONE_VERIFY", "VERIFICATION_BUSY_NUMBER", "VERIFICATION_LOADING", "VK_MAIL_CREATE", "PHONE_2FA_VERIFY", "PHONE_2FA_VERIFY_SMS", "PHONE_2FA_VERIFY_APP", "PHONE_2FA_VERIFY_CALL", "PHONE_2FA_VERIFY_LIB", "PARTIAL_EXPAND_ENTER_PASSWORD", "PARTIAL_EXPAND_HAVE_ACCOUNT", "PARTIAL_SILENT_EXPAND_PASSWORD", "OAUTH_EXISTING_ACCOUNT", "OAUTH_REGISTRATION_PHONE", "OAUTH_APPLE", "OAUTH_MAIL", "OAUTH_OK", "OAUTH_SBER", "OAUTH_ESIA", "OTHER", "START", "START_PROCEED_AS", "START_PROCEED_AS_MASTER_ACCOUNT", "START_WITH_PHONE", "SUGGEST_VK_ID_VALUE", "SILENT_AUTH", "SILENT_AUTH_LOADING", "SILENT_AUTH_EXISTING_ACCOUNT", "SILENT_AUTH_PROVIDED_PHONE", "SILENT_AUTH_MIGRATION", "SILENT_AUTH_EMAIL", "VERIFICATION_AUTHENTICATOR_CODE", "VKC_ACCOUNT_LINK_LOADING", "VKC_ACCOUNT_NOT_FOUND", "VKC_ACCOUNT_FOUND", "VKC_ACCOUNT_ALREADY_LINKED", "VKC_ACCOUNT_LINK_TOKEN_ERROR", "VKC_ACCOUNT_LINK_PASSWORD", "VKC_ACCOINT_MANY_CHOICES", "VKC_DATA_PERMISSION", "AUTH_PASSWORD", "AUTH_START_LOADING", "VKID_USER_CONFIRMATION", "VERIFICATION_CALL_CODE", "CONSENT_SCREEN_AGREEMENT", "QR_CODE_ASK_CONFIRM", "QR_CODE_MAP", "ALERT_QR_CODE_IRRELEVANT", "ALERT_AUTH_UNKNOWN_ERROR", "ALERT_AUTH_NETWORK_ERROR", "ALERT_AUTH_FLOOD_CONTROL_ERROR", "VK_PAY_CHECKOUT", "ENTRY_ASK_CONFIRM", "ENTRY_MAP", "ALERT_AUTH_SUCCESS", "UXPOLL_MODAL", "PROCEED_AS_WITH_SUBPROFILE", "MULTI_ACC_ADD_ACCOUNT", "MULTI_ACC_SWITCHER", "MULTIACC_SELECTOR", "ONBOARDING_MULTIACCOUNT", "ONBOARDING_LONGTAP_MULTIACCOUNT", "ALERT_AUTH_PHONE", "OAUTH_YANDEX", "ONBOARDING_ESIA", "ERROR_CONNECTION_TO_ESIA", "CONNECT_ACCOUNTS_VKID_ESIA_START", "ESIA_LINKED_TO_ANOTHER_VKID", "ESIA_NOT_VERIFIED", "REQUEST_SYNCHRONIZE_DATA_VKID_ESIA", "CONNECT_ACCOUNTS_VKID_ESIA_SUCCESS", "ESIA_AUTH_ACTIVATED_SUCCESS", "PHONE_CHANGE_ACCOUNT", "EXTERNAL_INVALID_PROFILE", "ALERT_UNLINK_PHONE_NUMBER", "ALERT_PHONE_SUCCESS_VERIFICATION", "ALERT_SUCCESS_UNLINK_PHONE_NUMBER", "PROFILE", "ESIA_TRUSTED_PROFILE", "CELEBRITY_PROFILE", "CELEBRITY_VERIFICATION_FAQ", "ESIA_FAQ", "OAUTH_TINKOFF", "ONBOARDING_VERIFICATION", "VERIFICATION_ERROR_CONNECTION", "CONNECT_ACCOUNTS_VKID_OAUTH_START", "OAUTH_LINKED_TO_ANOTHER_VKID", "OAUTH_NOT_VERIFIED", "REQUEST_SYNCHRONIZE_DATA_VKID_OAUTH", "OAUTH_ACTIVATED_SUCCESS", "PASSWORD_CUA", "SMS_PROCESS_CUA", "CALLRESET_PROCESS_CUA", "VERIFICATIONS", "CONNECT_ACCOUNTS_VKID_OAUTH_SUCCESS", "VERIFICATION_PASSKEY", "CONFIRM_AUTH_FAILED", "EXTENDED_RESTORE", "ALERT_KEYS_NOT_SUPPORTED", "SETTINGS_LOGOUT", "SETTINGS", "OAUTH_GOOGLE", "REGISTRATION_USECASE", "REGISTRATION_IS_FIRST_ACCOUNT", "ONBOARDING_USECASE", "MEET_PASSKEY", "CONNECT_PASSKEY", "CONNECTED_KEYS", "REGISTRATION_SERVICE_USER_ADD", "PRIMARY_FACTOR_CHOICE", "LINK_AVAILABLE_MAIL", "SERVICE_MENU", "ALERT_USER_BLOCKED", "ALERT_USER_DELETED", "ALERT_TRY_AGAIN", "OAUTH_ALFA", "EMAIL_VERIFICATION", "MAIL_LINKED_ANOTHER_ACCOUNT", "ECOSYSTEM_NAVIGATION", "ECOSYSTEM_NAVIGATION_PROFILE", "ECOSYSTEM_NAVIGATION_ACCOUNT_VIEW", "UNBLOCK_PROTECT_ACCOUNT", "CALLRESET_WARNING", "START_VKME", "MOBILE_QR_AUTH_CODE_GUIDE", "MOBILE_QR_ALERT_AUTH_ERROR", "QR_SCANNER", "QR_CODE_CONFIRM_WAITING", "ALERT_ACCOUNTS_LIMIT_REACHED", "MOBILE_QR_ALERT_INCORRECT_QR_SCANNED", "PINCODE_VALIDATION_ENTER", "PINCODE_TOO_MANY_ATTEMPTS_ALERT", "PINCODE_CHANGE_NEW_PINCODE", "VKME_ADD_ACCOUNT", "AUTHORIZATION_PHONE", "PROMO_ONEPASS", "ONBOARDING_RELATED", "AUTH_MOBILE_QR_CODE_APP", "RESTORE_AUTH_MAIL", "RESTORE_EMAIL_MAIL", "SWITCH_VKID_MODAL_WINDOW", BeforeRecoveryVKIDViewModel.BEFORE_RECOVERY_BLOCKED_EMAIL, "OAUTH_TO_GRAY_VKID_BIND", "GRAY_VKID_TO_OAUTH_BIND", "DOUBTFUL_AUTH", "CONFIRM_ACTUAL_PHONE_BY_USER", "CONFIRM_ACTUAL_EMAIL_BY_USER", "ADD_EMAIL_BY_USER", "START_MAIL", "SILENT_AUTH_MAIL", "CONTINUE_AUTH_CHOICE_BAR", "EMAIL_CHOICE_BAR", "PASSWORDLESS_AUTH_CHOICE", "PASSWORDLESS_AUTH_PROMO", "GRAY_LINK_MAIL_PASSWORD", "SMS_INBOX_ERROR", "SMS_INBOX_CHECKING", "QR_CODE_DISPLAY_CODE", "QR_CODE_DEVICE_INFO", "VKID_FAQ", "DEVICE_CODE_INPUT", "DEVICE_CODE_ASK_CONFIRM", "DEVICE_CODE_DISPLAY", "sak_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public enum EventScreen {
        ACCOUNT_CONFIRM_PASSWORD("account_confirm_password"),
        ACCOUNT_CONFIRM_VERIFY("account_confirm_verify"),
        AUTH_QR_CODE("auth_qr_code"),
        BANNED_ACCOUNT("banned_account"),
        CAPTCHA("captcha"),
        CONTACTS_APPS_ADD_PHONE("contacts_apps_add_phone"),
        CONTACTS_APPS_ADD_EMAIL("contacts_apps_add_email"),
        CONTACTS_APPS_ADD_ADDRESS("contacts_apps_add_address"),
        CONTACTS_APPS_EDIT_PHONE("contacts_apps_edit_phone"),
        CONTACTS_APPS_EDIT_EMAIL("contacts_apps_edit_email"),
        CONTACTS_APPS_EDIT_ADDRESS("contacts_apps_edit_address"),
        CONSENT_SCREEN("consent_screen"),
        NOWHERE_DIALOG("nowhere_dialog"),
        FAST_SILENT_AUTH_EXISTING_ACCOUNT("fast_silent_auth_existing_account"),
        FAST_SILENT_AUTH_AS_USER("fast_silent_auth_as_user"),
        FAST_SILENT_AUTH_DOWNLOAD("fast_silent_auth_download"),
        FAST_SILENT_AUTH_SUCCESS("fast_silent_auth_success"),
        FAST_SILENT_AUTH_ERROR("fast_silent_auth_error"),
        GAME(BonusConstants.GAME_NAME),
        MINI_APP("mini_app"),
        NOWHERE("nowhere"),
        PASSPORT_RESTORE("passport_restore"),
        REGISTRATION_PHONE("registration_phone"),
        PROMO_MAX("promo_max"),
        REGISTRATION_PERMISSION("registration_permission"),
        REGISTRATION_EXISTENT_ACCOUNT_NO_PASSWORD_OK("registration_existent_account_no_password_ok"),
        REGISTRATION_EXISTENT_ACCOUNT_PASSWORDLESS("registration_existent_account_passwordless"),
        REGISTRATION_CONNECT_GMAIL("registration_connect_gmail"),
        REGISTRATION_PHONE_VERIFY("registration_phone_verify"),
        REGISTRATION_PHONE_VERIFY_LIB("registration_phone_verify_lib"),
        REGISTRATION_NAME("registration_name"),
        REGISTRATION_NAME_ADD("registration_name_add"),
        REGISTRATION_INFO_ABOUT_YOURSELF("registration_info_about_yourself"),
        REGISTRATION_INFO_ABOUT_YOURSELF_ADD("registration_info_about_yourself_add"),
        REGISTRATION_EXISTENT_ACCOUNT("registration_existent_account"),
        REGISTRATION_EXISTENT_ACCOUNT_NO_PASSWORD("registration_existent_account_no_password"),
        REGISTRATION_BDAY("registration_bday"),
        REGISTRATION_BDAY_ADD("registration_bday_add"),
        REGISTRATION_PASSWORD("registration_password"),
        REGISTRATION_PASSWORD_ADD("registration_password_add"),
        REGISTRATION_IMPORT_CONTACTS("registration_import_contacts"),
        REGISTRATION_CONNECT_FACEBOOK("registration_connect_facebook"),
        REGISTRATION_CONNECT_OK("registration_connect_ok"),
        REGISTRATION_CONNECT_TWITTER("registration_connect_twitter"),
        REGISTRATION_PHOTO("registration_photo"),
        REGISTRATION_CHOOSE_PHOTO("registration_choose_photo"),
        REGISTRATION_TAKE_PHOTO("registration_take_photo"),
        REGISTRATION_STYLE_PHOTO("registration_style_photo"),
        REGISTRATION_CROP_PHOTO("registration_crop_photo"),
        REGISTRATION_LIST_ADDRESS_BOOK("registration_list_address_book"),
        REGISTRATION_LIST_FRIENDS_FACEBOOK("registration_list_friends_facebook"),
        REGISTRATION_LIST_FRIENDS_OK("registration_list_friends_ok"),
        REGISTRATION_LIST_FRIENDS_TWITTER("registration_list_friends_twitter"),
        REGISTRATION_LIST_CONTACTS_GMAIL("registration_list_contacts_gmail"),
        REGISTRATION_PUSH("registration_push"),
        REGISTRATION_GEO("registration_geo"),
        REGISTRATION_PUSH_REQUEST("registration_push_request"),
        REGISTRATION_SUBJECTS("registration_subjects"),
        REGISTRATION_EMAIL_VERIFY("registration_email_verify"),
        REGISTRATION_EMAIL_PASSWORD("registration_email_password"),
        REGISTRATION_EMAIL("registration_email"),
        REGISTRATION_EMAIL_EXPLANATION("registration_email_explanation"),
        REGISTRATION_NEW_ACCOUNT("registration_new_account"),
        REGISTRATION_EXISTENT_ACCOUNT_RESTORE("registration_existent_account_restore"),
        LK_PASSWORD("lk_password"),
        RESTORE_ACCOUNT("restore_account"),
        HAVE_ACCOUNT_QUESTION("have_account_question"),
        HAVE_ACCOUNT_CREDENTIALS("have_account_credentials"),
        HAVE_ACCOUNT_SUPPORT("have_account_support"),
        CONTACTING_SUPPORT("contacting_support"),
        VERIFICATION_ASK_NUMBER("verification_ask_number"),
        VERIFICATION_ENTER_NUMBER("verification_enter_number"),
        VERIFICATION_PHONE_VERIFY("verification_phone_verify"),
        VERIFICATION_BUSY_NUMBER("verification_busy_number"),
        VERIFICATION_LOADING("verification_loading"),
        VK_MAIL_CREATE("vk_mail_create"),
        PHONE_2FA_VERIFY("phone_2fa_verify"),
        PHONE_2FA_VERIFY_SMS("phone_2fa_verify_sms"),
        PHONE_2FA_VERIFY_APP("phone_2fa_verify_app"),
        PHONE_2FA_VERIFY_CALL("phone_2fa_verify_call"),
        PHONE_2FA_VERIFY_LIB("phone_2fa_verify_lib"),
        PARTIAL_EXPAND_ENTER_PASSWORD("partial_expand_enter_password"),
        PARTIAL_EXPAND_HAVE_ACCOUNT("partial_expand_have_account"),
        PARTIAL_SILENT_EXPAND_PASSWORD("partial_silent_expand_password"),
        OAUTH_EXISTING_ACCOUNT("oauth_existing_account"),
        OAUTH_REGISTRATION_PHONE("oauth_registration_phone"),
        OAUTH_APPLE("oauth_apple"),
        OAUTH_MAIL("oauth_mail"),
        OAUTH_OK("oauth_ok"),
        OAUTH_SBER("oauth_sber"),
        OAUTH_ESIA("oauth_esia"),
        OTHER("other"),
        START("start"),
        START_PROCEED_AS("start_proceed_as"),
        START_PROCEED_AS_MASTER_ACCOUNT("start_proceed_as_master_account"),
        START_WITH_PHONE("start_with_phone"),
        SUGGEST_VK_ID_VALUE("suggest_vk_id_value"),
        SILENT_AUTH("silent_auth"),
        SILENT_AUTH_LOADING("silent_auth_loading"),
        SILENT_AUTH_EXISTING_ACCOUNT("silent_auth_existing_account"),
        SILENT_AUTH_PROVIDED_PHONE("silent_auth_provided_phone"),
        SILENT_AUTH_MIGRATION("silent_auth_migration"),
        SILENT_AUTH_EMAIL("silent_auth_email"),
        VERIFICATION_AUTHENTICATOR_CODE("verification_authenticator_code"),
        VKC_ACCOUNT_LINK_LOADING("vkc_account_link_loading"),
        VKC_ACCOUNT_NOT_FOUND("vkc_account_not_found"),
        VKC_ACCOUNT_FOUND("vkc_account_found"),
        VKC_ACCOUNT_ALREADY_LINKED("vkc_account_already_linked"),
        VKC_ACCOUNT_LINK_TOKEN_ERROR("vkc_account_link_token_error"),
        VKC_ACCOUNT_LINK_PASSWORD("vkc_account_link_password"),
        VKC_ACCOINT_MANY_CHOICES("vkc_accoint_many_choices"),
        VKC_DATA_PERMISSION("vkc_data_permission"),
        AUTH_PASSWORD("auth_password"),
        AUTH_START_LOADING("auth_start_loading"),
        VKID_USER_CONFIRMATION("vkid_user_confirmation"),
        VERIFICATION_CALL_CODE("verification_call_code"),
        CONSENT_SCREEN_AGREEMENT("consent_screen_agreement"),
        QR_CODE_ASK_CONFIRM("qr_code_ask_confirm"),
        QR_CODE_MAP("qr_code_map"),
        ALERT_QR_CODE_IRRELEVANT("alert_qr_code_irrelevant"),
        ALERT_AUTH_UNKNOWN_ERROR("alert_auth_unknown_error"),
        ALERT_AUTH_NETWORK_ERROR("alert_auth_network_error"),
        ALERT_AUTH_FLOOD_CONTROL_ERROR("alert_auth_flood_control_error"),
        VK_PAY_CHECKOUT("vk_pay_checkout"),
        ENTRY_ASK_CONFIRM("entry_ask_confirm"),
        ENTRY_MAP("entry_map"),
        ALERT_AUTH_SUCCESS("alert_auth_success"),
        UXPOLL_MODAL("uxpoll_modal"),
        PROCEED_AS_WITH_SUBPROFILE("proceed_as_with_subprofile"),
        MULTI_ACC_ADD_ACCOUNT("multi_acc_add_account"),
        MULTI_ACC_SWITCHER("multi_acc_switcher"),
        MULTIACC_SELECTOR("multiacc_selector"),
        ONBOARDING_MULTIACCOUNT("onboarding_multiaccount"),
        ONBOARDING_LONGTAP_MULTIACCOUNT("onboarding_longtap_multiaccount"),
        ALERT_AUTH_PHONE("alert_auth_phone"),
        OAUTH_YANDEX("oauth_yandex"),
        ONBOARDING_ESIA("onboarding_esia"),
        ERROR_CONNECTION_TO_ESIA("error_connection_to_esia"),
        CONNECT_ACCOUNTS_VKID_ESIA_START("connect_accounts_vkid_esia_start"),
        ESIA_LINKED_TO_ANOTHER_VKID("esia_linked_to_another_vkid"),
        ESIA_NOT_VERIFIED("esia_not_verified"),
        REQUEST_SYNCHRONIZE_DATA_VKID_ESIA("request_synchronize_data_vkid_esia"),
        CONNECT_ACCOUNTS_VKID_ESIA_SUCCESS("connect_accounts_vkid_esia_success"),
        ESIA_AUTH_ACTIVATED_SUCCESS("esia_auth_activated_success"),
        PHONE_CHANGE_ACCOUNT("phone_change_account"),
        EXTERNAL_INVALID_PROFILE("external_invalid_profile"),
        ALERT_UNLINK_PHONE_NUMBER("alert_unlink_phone_number"),
        ALERT_PHONE_SUCCESS_VERIFICATION("alert_phone_success_verification"),
        ALERT_SUCCESS_UNLINK_PHONE_NUMBER("alert_success_unlink_phone_number"),
        PROFILE("profile"),
        ESIA_TRUSTED_PROFILE("esia_trusted_profile"),
        CELEBRITY_PROFILE("celebrity_profile"),
        CELEBRITY_VERIFICATION_FAQ("celebrity_verification_faq"),
        ESIA_FAQ("esia_faq"),
        OAUTH_TINKOFF("oauth_tinkoff"),
        ONBOARDING_VERIFICATION("onboarding_verification"),
        VERIFICATION_ERROR_CONNECTION("verification_error_connection"),
        CONNECT_ACCOUNTS_VKID_OAUTH_START("connect_accounts_vkid_oauth_start"),
        OAUTH_LINKED_TO_ANOTHER_VKID("oauth_linked_to_another_vkid"),
        OAUTH_NOT_VERIFIED("oauth_not_verified"),
        REQUEST_SYNCHRONIZE_DATA_VKID_OAUTH("request_synchronize_data_vkid_oauth"),
        OAUTH_ACTIVATED_SUCCESS("oauth_activated_success"),
        PASSWORD_CUA("password-cua"),
        SMS_PROCESS_CUA("sms-process-cua"),
        CALLRESET_PROCESS_CUA("callreset-process-cua"),
        VERIFICATIONS("verifications"),
        CONNECT_ACCOUNTS_VKID_OAUTH_SUCCESS("connect_accounts_vkid_oauth_success"),
        VERIFICATION_PASSKEY("verification_passkey"),
        CONFIRM_AUTH_FAILED("confirm_auth_failed"),
        EXTENDED_RESTORE("extended_restore"),
        ALERT_KEYS_NOT_SUPPORTED("alert_keys_not_supported"),
        SETTINGS_LOGOUT("settings-logout"),
        SETTINGS("settings"),
        OAUTH_GOOGLE("oauth_google"),
        REGISTRATION_USECASE("registration_usecase"),
        REGISTRATION_IS_FIRST_ACCOUNT("registration_is_first_account"),
        ONBOARDING_USECASE("onboarding_usecase"),
        MEET_PASSKEY("meet_passkey"),
        CONNECT_PASSKEY("connect_passkey"),
        CONNECTED_KEYS("connected_keys"),
        REGISTRATION_SERVICE_USER_ADD("registration_service_user_add"),
        PRIMARY_FACTOR_CHOICE("primary_factor_choice"),
        LINK_AVAILABLE_MAIL("link_available_mail"),
        SERVICE_MENU("service_menu"),
        ALERT_USER_BLOCKED("alert_user_blocked"),
        ALERT_USER_DELETED("alert_user_deleted"),
        ALERT_TRY_AGAIN("alert_try_again"),
        OAUTH_ALFA("oauth_alfa"),
        EMAIL_VERIFICATION("email_verification"),
        MAIL_LINKED_ANOTHER_ACCOUNT("mail_linked_another_account"),
        ECOSYSTEM_NAVIGATION("ecosystem_navigation"),
        ECOSYSTEM_NAVIGATION_PROFILE("ecosystem_navigation_profile"),
        ECOSYSTEM_NAVIGATION_ACCOUNT_VIEW("ecosystem_navigation_account_view"),
        UNBLOCK_PROTECT_ACCOUNT("unblock_protect_account"),
        CALLRESET_WARNING("callreset_warning"),
        START_VKME("start_vkme"),
        MOBILE_QR_AUTH_CODE_GUIDE("mobile_qr_auth_code_guide"),
        MOBILE_QR_ALERT_AUTH_ERROR("mobile_qr_alert_auth_error"),
        QR_SCANNER("qr_scanner"),
        QR_CODE_CONFIRM_WAITING("qr_code_confirm_waiting"),
        ALERT_ACCOUNTS_LIMIT_REACHED("alert_accounts_limit_reached"),
        MOBILE_QR_ALERT_INCORRECT_QR_SCANNED("mobile_qr_alert_incorrect_qr_scanned"),
        PINCODE_VALIDATION_ENTER("pincode_validation_enter"),
        PINCODE_TOO_MANY_ATTEMPTS_ALERT("pincode_too_many_attempts_alert"),
        PINCODE_CHANGE_NEW_PINCODE("pincode_change_new_pincode"),
        VKME_ADD_ACCOUNT("vkme_add_account"),
        AUTHORIZATION_PHONE("authorization_phone"),
        PROMO_ONEPASS("promo_onepass"),
        ONBOARDING_RELATED("onboarding_related"),
        AUTH_MOBILE_QR_CODE_APP("auth_mobile_qr_code_app"),
        RESTORE_AUTH_MAIL("restore_auth_mail"),
        RESTORE_EMAIL_MAIL("restore_email_mail"),
        SWITCH_VKID_MODAL_WINDOW("switch_vkid_modal_window"),
        BLOCKED_EMAIL("blocked_email"),
        OAUTH_TO_GRAY_VKID_BIND("oauth_to_gray_vkid_bind"),
        GRAY_VKID_TO_OAUTH_BIND("gray_vkid_to_oauth_bind"),
        DOUBTFUL_AUTH("doubtful_auth"),
        CONFIRM_ACTUAL_PHONE_BY_USER("confirm_actual_phone_by_user"),
        CONFIRM_ACTUAL_EMAIL_BY_USER("confirm_actual_email_by_user"),
        ADD_EMAIL_BY_USER("add_email_by_user"),
        START_MAIL("start_mail"),
        SILENT_AUTH_MAIL("silent_auth_mail"),
        CONTINUE_AUTH_CHOICE_BAR("continue_auth_choice_bar"),
        EMAIL_CHOICE_BAR("email_choice_bar"),
        PASSWORDLESS_AUTH_CHOICE("passwordless_auth_choice"),
        PASSWORDLESS_AUTH_PROMO("passwordless_auth_promo"),
        GRAY_LINK_MAIL_PASSWORD("gray_link_mail_password"),
        SMS_INBOX_ERROR("sms_inbox_error"),
        SMS_INBOX_CHECKING("sms_inbox_checking"),
        QR_CODE_DISPLAY_CODE("qr_code_display_code"),
        QR_CODE_DEVICE_INFO("qr_code_device_info"),
        VKID_FAQ("vkid_faq"),
        DEVICE_CODE_INPUT("device_code_input"),
        DEVICE_CODE_ASK_CONFIRM("device_code_ask_confirm"),
        DEVICE_CODE_DISPLAY("device_code_display");

        private static final /* synthetic */ EnumEntries kastatsbilkvmocb = EnumEntriesKt.enumEntries(kastatsbilkvmoca());

        @NotNull
        private final String kastatsbilkvmocc;

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J&\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u00022\b\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016¨\u0006\f"}, d2 = {"Lcom/vk/stat/sak/scheme/SchemeStatSak$EventScreen$Serializer;", "Lcom/google/gson/JsonSerializer;", "Lcom/vk/stat/sak/scheme/SchemeStatSak$EventScreen;", "<init>", "()V", "serialize", "Lcom/google/gson/JsonElement;", "src", "typeOfSrc", "Ljava/lang/reflect/Type;", "context", "Lcom/google/gson/JsonSerializationContext;", "sak_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
        @SourceDebugExtension({"SMAP\nSchemeStatSak.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SchemeStatSak.kt\ncom/vk/stat/sak/scheme/SchemeStatSak$EventScreen$Serializer\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,3265:1\n1#2:3266\n*E\n"})
        public static final class Serializer implements JsonSerializer<EventScreen> {
            @Override // com.google.gson.JsonSerializer
            @NotNull
            public JsonElement serialize(@Nullable EventScreen src, @Nullable Type typeOfSrc, @Nullable JsonSerializationContext context) {
                if (src != null) {
                    return new JsonPrimitive(src.kastatsbilkvmocc);
                }
                JsonNull INSTANCE = JsonNull.INSTANCE;
                Intrinsics.checkNotNullExpressionValue(INSTANCE, "INSTANCE");
                return INSTANCE;
            }
        }

        EventScreen(String str) {
            this.kastatsbilkvmocc = str;
        }

        @NotNull
        public static EnumEntries<EventScreen> getEntries() {
            return kastatsbilkvmocb;
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\bf\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lcom/vk/stat/sak/scheme/SchemeStatSak$NavigationPayload;", "", "sak_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public interface NavigationPayload {
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes12.dex */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\n\b\u0086\b\u0018\u00002\u00020\u0001:\u0001\u001cB\u001b\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\b\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\b\u0010\tJ\u0012\u0010\n\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0004\b\n\u0010\u000bJ&\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004HÆ\u0001¢\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u000e\u001a\u00020\u0004HÖ\u0001¢\u0006\u0004\b\u000e\u0010\u000bJ\u0010\u0010\u0010\u001a\u00020\u000fHÖ\u0001¢\u0006\u0004\b\u0010\u0010\u0011J\u001a\u0010\u0014\u001a\u00020\u00132\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0003\u001a\u00020\u00028\u0006X\u0087\u0004¢\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\tR\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004¢\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u000b¨\u0006\u001d"}, d2 = {"Lcom/vk/stat/sak/scheme/SchemeStatSak$MultiaccountFieldItem;", "", "Lcom/vk/stat/sak/scheme/SchemeStatSak$MultiaccountFieldItem$Name;", "name", "", "value", "<init>", "(Lcom/vk/stat/sak/scheme/SchemeStatSak$MultiaccountFieldItem$Name;Ljava/lang/String;)V", "component1", "()Lcom/vk/stat/sak/scheme/SchemeStatSak$MultiaccountFieldItem$Name;", "component2", "()Ljava/lang/String;", "copy", "(Lcom/vk/stat/sak/scheme/SchemeStatSak$MultiaccountFieldItem$Name;Ljava/lang/String;)Lcom/vk/stat/sak/scheme/SchemeStatSak$MultiaccountFieldItem;", "toString", "", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "kastatsbilkvmoca", "Lcom/vk/stat/sak/scheme/SchemeStatSak$MultiaccountFieldItem$Name;", "getName", "kastatsbilkvmocb", "Ljava/lang/String;", "getValue", "Name", "sak_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final /* data */ class MultiaccountFieldItem {

        /* JADX INFO: renamed from: kastatsbilkvmoca, reason: from kotlin metadata */
        @SerializedName("name")
        @NotNull
        private final Name name;

        /* JADX INFO: renamed from: kastatsbilkvmocb, reason: from kotlin metadata */
        @SerializedName("value")
        @Nullable
        private final String value;

        /* JADX WARN: Enum visitor error
        jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v1 com.vk.stat.sak.scheme.SchemeStatSak$MultiaccountFieldItem$Name[], still in use, count: 1, list:
          (r0v1 com.vk.stat.sak.scheme.SchemeStatSak$MultiaccountFieldItem$Name[]) from 0x0024: INVOKE (r0v1 com.vk.stat.sak.scheme.SchemeStatSak$MultiaccountFieldItem$Name[]) STATIC call: kotlin.enums.EnumEntriesKt.enumEntries(java.lang.Enum[]):kotlin.enums.EnumEntries A[MD:<E extends java.lang.Enum<E>>:(E extends java.lang.Enum<E>[]):kotlin.enums.EnumEntries<E extends java.lang.Enum<E>> (m), WRAPPED] (LINE:37)
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
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001j\u0002\b\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, d2 = {"Lcom/vk/stat/sak/scheme/SchemeStatSak$MultiaccountFieldItem$Name;", "", "TO_SWITCHER_FROM", "FROM_PROFILE_TYPE", "TO_PROFILE_TYPE", "sak_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
        public static final class Name {
            TO_SWITCHER_FROM,
            FROM_PROFILE_TYPE,
            TO_PROFILE_TYPE;

            private static final /* synthetic */ EnumEntries kastatsbilkvmocb;

            static {
                kastatsbilkvmocb = EnumEntriesKt.enumEntries(nameArr);
            }

            private Name() {
                super(str, i);
            }

            @NotNull
            public static EnumEntries<Name> getEntries() {
                return kastatsbilkvmocb;
            }

            public static Name valueOf(String str) {
                return (Name) Enum.valueOf(Name.class, str);
            }

            public static Name[] values() {
                return (Name[]) kastatsbilkvmoca.clone();
            }
        }

        public MultiaccountFieldItem(@NotNull Name name, @Nullable String str) {
            Intrinsics.checkNotNullParameter(name, "name");
            this.name = name;
            this.value = str;
        }

        public static /* synthetic */ MultiaccountFieldItem copy$default(MultiaccountFieldItem multiaccountFieldItem, Name name, String str, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                name = multiaccountFieldItem.name;
            }
            if ((i10 & 2) != 0) {
                str = multiaccountFieldItem.value;
            }
            return multiaccountFieldItem.copy(name, str);
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Name getName() {
            return this.name;
        }

        @Nullable
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getValue() {
            return this.value;
        }

        @NotNull
        public final MultiaccountFieldItem copy(@NotNull Name name, @Nullable String value) {
            Intrinsics.checkNotNullParameter(name, "name");
            return new MultiaccountFieldItem(name, value);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof MultiaccountFieldItem)) {
                return false;
            }
            MultiaccountFieldItem multiaccountFieldItem = (MultiaccountFieldItem) other;
            return this.name == multiaccountFieldItem.name && Intrinsics.areEqual(this.value, multiaccountFieldItem.value);
        }

        @NotNull
        public final Name getName() {
            return this.name;
        }

        @Nullable
        public final String getValue() {
            return this.value;
        }

        public int hashCode() {
            int iHashCode = this.name.hashCode() * 31;
            String str = this.value;
            return iHashCode + (str == null ? 0 : str.hashCode());
        }

        @NotNull
        public String toString() {
            return "MultiaccountFieldItem(name=" + this.name + ", value=" + this.value + ')';
        }

        public /* synthetic */ MultiaccountFieldItem(Name name, String str, int i10, DefaultConstructorMarker defaultConstructorMarker) {
            this(name, (i10 & 2) != 0 ? null : str);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes12.dex */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\n\b\u0086\b\u0018\u00002\u00020\u0001:\u0001\u001cB\u001b\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\b\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\b\u0010\tJ\u0012\u0010\n\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0004\b\n\u0010\u000bJ&\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004HÆ\u0001¢\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u000e\u001a\u00020\u0004HÖ\u0001¢\u0006\u0004\b\u000e\u0010\u000bJ\u0010\u0010\u0010\u001a\u00020\u000fHÖ\u0001¢\u0006\u0004\b\u0010\u0010\u0011J\u001a\u0010\u0014\u001a\u00020\u00132\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0003\u001a\u00020\u00028\u0006X\u0087\u0004¢\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\tR\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004¢\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u000b¨\u0006\u001d"}, d2 = {"Lcom/vk/stat/sak/scheme/SchemeStatSak$SakSessionsEventFieldItem;", "", "Lcom/vk/stat/sak/scheme/SchemeStatSak$SakSessionsEventFieldItem$Name;", "name", "", "value", "<init>", "(Lcom/vk/stat/sak/scheme/SchemeStatSak$SakSessionsEventFieldItem$Name;Ljava/lang/String;)V", "component1", "()Lcom/vk/stat/sak/scheme/SchemeStatSak$SakSessionsEventFieldItem$Name;", "component2", "()Ljava/lang/String;", "copy", "(Lcom/vk/stat/sak/scheme/SchemeStatSak$SakSessionsEventFieldItem$Name;Ljava/lang/String;)Lcom/vk/stat/sak/scheme/SchemeStatSak$SakSessionsEventFieldItem;", "toString", "", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "kastatsbilkvmoca", "Lcom/vk/stat/sak/scheme/SchemeStatSak$SakSessionsEventFieldItem$Name;", "getName", "kastatsbilkvmocb", "Ljava/lang/String;", "getValue", "Name", "sak_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final /* data */ class SakSessionsEventFieldItem {

        /* JADX INFO: renamed from: kastatsbilkvmoca, reason: from kotlin metadata */
        @SerializedName("name")
        @NotNull
        private final Name name;

        /* JADX INFO: renamed from: kastatsbilkvmocb, reason: from kotlin metadata */
        @SerializedName("value")
        @Nullable
        private final String value;

        /* JADX WARN: Enum visitor error
        jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v1 com.vk.stat.sak.scheme.SchemeStatSak$SakSessionsEventFieldItem$Name[], still in use, count: 1, list:
          (r0v1 com.vk.stat.sak.scheme.SchemeStatSak$SakSessionsEventFieldItem$Name[]) from 0x000d: INVOKE (r0v1 com.vk.stat.sak.scheme.SchemeStatSak$SakSessionsEventFieldItem$Name[]) STATIC call: kotlin.enums.EnumEntriesKt.enumEntries(java.lang.Enum[]):kotlin.enums.EnumEntries A[MD:<E extends java.lang.Enum<E>>:(E extends java.lang.Enum<E>[]):kotlin.enums.EnumEntries<E extends java.lang.Enum<E>> (m), WRAPPED] (LINE:14)
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
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0002\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001j\u0002\b\u0002¨\u0006\u0003"}, d2 = {"Lcom/vk/stat/sak/scheme/SchemeStatSak$SakSessionsEventFieldItem$Name;", "", "LIMIT_SETTINGS", "sak_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
        public static final class Name {
            LIMIT_SETTINGS;

            private static final /* synthetic */ EnumEntries kastatsbilkvmocb;

            static {
                kastatsbilkvmocb = EnumEntriesKt.enumEntries(nameArr);
            }

            private Name() {
                super("LIMIT_SETTINGS", 0);
            }

            @NotNull
            public static EnumEntries<Name> getEntries() {
                return kastatsbilkvmocb;
            }

            public static Name valueOf(String str) {
                return (Name) Enum.valueOf(Name.class, str);
            }

            public static Name[] values() {
                return (Name[]) kastatsbilkvmoca.clone();
            }
        }

        public SakSessionsEventFieldItem(@NotNull Name name, @Nullable String str) {
            Intrinsics.checkNotNullParameter(name, "name");
            this.name = name;
            this.value = str;
        }

        public static /* synthetic */ SakSessionsEventFieldItem copy$default(SakSessionsEventFieldItem sakSessionsEventFieldItem, Name name, String str, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                name = sakSessionsEventFieldItem.name;
            }
            if ((i10 & 2) != 0) {
                str = sakSessionsEventFieldItem.value;
            }
            return sakSessionsEventFieldItem.copy(name, str);
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Name getName() {
            return this.name;
        }

        @Nullable
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getValue() {
            return this.value;
        }

        @NotNull
        public final SakSessionsEventFieldItem copy(@NotNull Name name, @Nullable String value) {
            Intrinsics.checkNotNullParameter(name, "name");
            return new SakSessionsEventFieldItem(name, value);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SakSessionsEventFieldItem)) {
                return false;
            }
            SakSessionsEventFieldItem sakSessionsEventFieldItem = (SakSessionsEventFieldItem) other;
            return this.name == sakSessionsEventFieldItem.name && Intrinsics.areEqual(this.value, sakSessionsEventFieldItem.value);
        }

        @NotNull
        public final Name getName() {
            return this.name;
        }

        @Nullable
        public final String getValue() {
            return this.value;
        }

        public int hashCode() {
            int iHashCode = this.name.hashCode() * 31;
            String str = this.value;
            return iHashCode + (str == null ? 0 : str.hashCode());
        }

        @NotNull
        public String toString() {
            return "SakSessionsEventFieldItem(name=" + this.name + ", value=" + this.value + ')';
        }

        public /* synthetic */ SakSessionsEventFieldItem(Name name, String str, int i10, DefaultConstructorMarker defaultConstructorMarker) {
            this(name, (i10 & 2) != 0 ? null : str);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes12.dex */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\r\b\u0086\b\u0018\u00002\u00020\u0001:\u0001\"B'\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\n\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\n\u0010\u000bJ\u0012\u0010\f\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0004\b\f\u0010\rJ\u0012\u0010\u000e\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0004\b\u000e\u0010\u000fJ2\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÆ\u0001¢\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0012\u001a\u00020\u0004HÖ\u0001¢\u0006\u0004\b\u0012\u0010\rJ\u0010\u0010\u0013\u001a\u00020\u0006HÖ\u0001¢\u0006\u0004\b\u0013\u0010\u0014J\u001a\u0010\u0017\u001a\u00020\u00162\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0003\u001a\u00020\u00028\u0006X\u0087\u0004¢\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u000bR\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004¢\u0006\f\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001e\u0010\rR\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\f\n\u0004\b\u001f\u0010 \u001a\u0004\b!\u0010\u000f¨\u0006#"}, d2 = {"Lcom/vk/stat/sak/scheme/SchemeStatSak$NavigationFieldItem;", "", "Lcom/vk/stat/sak/scheme/SchemeStatSak$NavigationFieldItem$Name;", "name", "", "strValue", "", "intValue", "<init>", "(Lcom/vk/stat/sak/scheme/SchemeStatSak$NavigationFieldItem$Name;Ljava/lang/String;Ljava/lang/Integer;)V", "component1", "()Lcom/vk/stat/sak/scheme/SchemeStatSak$NavigationFieldItem$Name;", "component2", "()Ljava/lang/String;", "component3", "()Ljava/lang/Integer;", "copy", "(Lcom/vk/stat/sak/scheme/SchemeStatSak$NavigationFieldItem$Name;Ljava/lang/String;Ljava/lang/Integer;)Lcom/vk/stat/sak/scheme/SchemeStatSak$NavigationFieldItem;", "toString", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "kastatsbilkvmoca", "Lcom/vk/stat/sak/scheme/SchemeStatSak$NavigationFieldItem$Name;", "getName", "kastatsbilkvmocb", "Ljava/lang/String;", "getStrValue", "kastatsbilkvmocc", "Ljava/lang/Integer;", "getIntValue", "Name", "sak_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final /* data */ class NavigationFieldItem {

        /* JADX INFO: renamed from: kastatsbilkvmoca, reason: from kotlin metadata */
        @SerializedName("name")
        @NotNull
        private final Name name;

        /* JADX INFO: renamed from: kastatsbilkvmocb, reason: from kotlin metadata */
        @SerializedName("str_value")
        @Nullable
        private final String strValue;

        /* JADX INFO: renamed from: kastatsbilkvmocc, reason: from kotlin metadata */
        @SerializedName("int_value")
        @Nullable
        private final Integer intValue;

        /* JADX WARN: Enum visitor error
        jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v16 com.vk.stat.sak.scheme.SchemeStatSak$NavigationFieldItem$Name[], still in use, count: 1, list:
          (r0v16 com.vk.stat.sak.scheme.SchemeStatSak$NavigationFieldItem$Name[]) from 0x00b8: INVOKE (r0v16 com.vk.stat.sak.scheme.SchemeStatSak$NavigationFieldItem$Name[]) STATIC call: kotlin.enums.EnumEntriesKt.enumEntries(java.lang.Enum[]):kotlin.enums.EnumEntries A[MD:<E extends java.lang.Enum<E>>:(E extends java.lang.Enum<E>[]):kotlin.enums.EnumEntries<E extends java.lang.Enum<E>> (m), WRAPPED] (LINE:185)
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
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0011\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001j\u0002\b\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011¨\u0006\u0012"}, d2 = {"Lcom/vk/stat/sak/scheme/SchemeStatSak$NavigationFieldItem$Name;", "", "CLOSE_TAB", "ESIA_AWAY", "LEAVE_UNCHANGED", "ESIA_SYNCHRONIZED_DATA", "OAUTH_SYNCHRONIZED_DATA", "ESIA_TRUSTED", "VERIFICATION_AWAY", "VERIFICATION_OAUTH", "MULTIACC_SETTINGS", "MAIL_MOBILE", "MAIL_WEB", "JUMP_DESTINATION", "PASSWORD", "NOTIFICATION_SETTINGS", "NUMBER_OF_ACCOUNTS", "TRANSITION_ACCOUNT", "sak_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
        public static final class Name {
            CLOSE_TAB,
            ESIA_AWAY,
            LEAVE_UNCHANGED,
            ESIA_SYNCHRONIZED_DATA,
            OAUTH_SYNCHRONIZED_DATA,
            ESIA_TRUSTED,
            VERIFICATION_AWAY,
            VERIFICATION_OAUTH,
            MULTIACC_SETTINGS,
            MAIL_MOBILE,
            MAIL_WEB,
            JUMP_DESTINATION,
            PASSWORD,
            NOTIFICATION_SETTINGS,
            NUMBER_OF_ACCOUNTS,
            TRANSITION_ACCOUNT;

            private static final /* synthetic */ EnumEntries kastatsbilkvmocb;

            static {
                kastatsbilkvmocb = EnumEntriesKt.enumEntries(nameArr);
            }

            private Name() {
                super(str, i);
            }

            @NotNull
            public static EnumEntries<Name> getEntries() {
                return kastatsbilkvmocb;
            }

            public static Name valueOf(String str) {
                return (Name) Enum.valueOf(Name.class, str);
            }

            public static Name[] values() {
                return (Name[]) kastatsbilkvmoca.clone();
            }
        }

        public NavigationFieldItem(@NotNull Name name, @Nullable String str, @Nullable Integer num) {
            Intrinsics.checkNotNullParameter(name, "name");
            this.name = name;
            this.strValue = str;
            this.intValue = num;
        }

        public static /* synthetic */ NavigationFieldItem copy$default(NavigationFieldItem navigationFieldItem, Name name, String str, Integer num, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                name = navigationFieldItem.name;
            }
            if ((i10 & 2) != 0) {
                str = navigationFieldItem.strValue;
            }
            if ((i10 & 4) != 0) {
                num = navigationFieldItem.intValue;
            }
            return navigationFieldItem.copy(name, str, num);
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Name getName() {
            return this.name;
        }

        @Nullable
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getStrValue() {
            return this.strValue;
        }

        @Nullable
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final Integer getIntValue() {
            return this.intValue;
        }

        @NotNull
        public final NavigationFieldItem copy(@NotNull Name name, @Nullable String strValue, @Nullable Integer intValue) {
            Intrinsics.checkNotNullParameter(name, "name");
            return new NavigationFieldItem(name, strValue, intValue);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof NavigationFieldItem)) {
                return false;
            }
            NavigationFieldItem navigationFieldItem = (NavigationFieldItem) other;
            return this.name == navigationFieldItem.name && Intrinsics.areEqual(this.strValue, navigationFieldItem.strValue) && Intrinsics.areEqual(this.intValue, navigationFieldItem.intValue);
        }

        @Nullable
        public final Integer getIntValue() {
            return this.intValue;
        }

        @NotNull
        public final Name getName() {
            return this.name;
        }

        @Nullable
        public final String getStrValue() {
            return this.strValue;
        }

        public int hashCode() {
            int iHashCode = this.name.hashCode() * 31;
            String str = this.strValue;
            int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
            Integer num = this.intValue;
            return iHashCode2 + (num != null ? num.hashCode() : 0);
        }

        @NotNull
        public String toString() {
            return "NavigationFieldItem(name=" + this.name + ", strValue=" + this.strValue + ", intValue=" + this.intValue + ')';
        }

        public /* synthetic */ NavigationFieldItem(Name name, String str, Integer num, int i10, DefaultConstructorMarker defaultConstructorMarker) {
            this(name, (i10 & 2) != 0 ? null : str, (i10 & 4) != 0 ? null : num);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes12.dex */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u000e\b\u0086\b\u0018\u00002\u00020\u0001:\u0001$B+\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\n\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\f\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u000e\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b\u000e\u0010\rJ\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0004\b\u000f\u0010\rJ:\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0004HÆ\u0001¢\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0012\u001a\u00020\u0004HÖ\u0001¢\u0006\u0004\b\u0012\u0010\rJ\u0010\u0010\u0014\u001a\u00020\u0013HÖ\u0001¢\u0006\u0004\b\u0014\u0010\u0015J\u001a\u0010\u0018\u001a\u00020\u00172\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0018\u0010\u0019R\u001a\u0010\u0003\u001a\u00020\u00028\u0006X\u0087\u0004¢\u0006\f\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u000bR\u001a\u0010\u0005\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\f\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010\rR\u001a\u0010\u0006\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\f\n\u0004\b \u0010\u001e\u001a\u0004\b!\u0010\rR\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004¢\u0006\f\n\u0004\b\"\u0010\u001e\u001a\u0004\b#\u0010\r¨\u0006%"}, d2 = {"Lcom/vk/stat/sak/scheme/SchemeStatSak$RegistrationFieldItem;", "", "Lcom/vk/stat/sak/scheme/SchemeStatSak$RegistrationFieldItem$Name;", "name", "", "startInteractionTime", "endInteractionTime", "value", "<init>", "(Lcom/vk/stat/sak/scheme/SchemeStatSak$RegistrationFieldItem$Name;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "component1", "()Lcom/vk/stat/sak/scheme/SchemeStatSak$RegistrationFieldItem$Name;", "component2", "()Ljava/lang/String;", "component3", "component4", "copy", "(Lcom/vk/stat/sak/scheme/SchemeStatSak$RegistrationFieldItem$Name;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/vk/stat/sak/scheme/SchemeStatSak$RegistrationFieldItem;", "toString", "", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "kastatsbilkvmoca", "Lcom/vk/stat/sak/scheme/SchemeStatSak$RegistrationFieldItem$Name;", "getName", "kastatsbilkvmocb", "Ljava/lang/String;", "getStartInteractionTime", "kastatsbilkvmocc", "getEndInteractionTime", "kastatsbilkvmocd", "getValue", "Name", "sak_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final /* data */ class RegistrationFieldItem {

        /* JADX INFO: renamed from: kastatsbilkvmoca, reason: from kotlin metadata */
        @SerializedName("name")
        @NotNull
        private final Name name;

        /* JADX INFO: renamed from: kastatsbilkvmocb, reason: from kotlin metadata */
        @SerializedName("start_interaction_time")
        @NotNull
        private final String startInteractionTime;

        /* JADX INFO: renamed from: kastatsbilkvmocc, reason: from kotlin metadata */
        @SerializedName("end_interaction_time")
        @NotNull
        private final String endInteractionTime;

        /* JADX INFO: renamed from: kastatsbilkvmocd, reason: from kotlin metadata */
        @SerializedName("value")
        @Nullable
        private final String value;

        /* JADX WARN: Enum visitor error
        jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v137 com.vk.stat.sak.scheme.SchemeStatSak$RegistrationFieldItem$Name[], still in use, count: 1, list:
          (r0v137 com.vk.stat.sak.scheme.SchemeStatSak$RegistrationFieldItem$Name[]) from 0x07cf: INVOKE (r0v137 com.vk.stat.sak.scheme.SchemeStatSak$RegistrationFieldItem$Name[]) STATIC call: kotlin.enums.EnumEntriesKt.enumEntries(java.lang.Enum[]):kotlin.enums.EnumEntries A[MD:<E extends java.lang.Enum<E>>:(E extends java.lang.Enum<E>[]):kotlin.enums.EnumEntries<E extends java.lang.Enum<E>> (m), WRAPPED] (LINE:2000)
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
        @Metadata(d1 = {"\u0000\r\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0003\b\u008a\u0001\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001j\u0002\b\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001dj\u0002\b\u001ej\u0002\b\u001fj\u0002\b j\u0002\b!j\u0002\b\"j\u0002\b#j\u0002\b$j\u0002\b%j\u0002\b&j\u0002\b'j\u0002\b(j\u0002\b)j\u0002\b*j\u0002\b+j\u0002\b,j\u0002\b-j\u0002\b.j\u0002\b/j\u0002\b0j\u0002\b1j\u0002\b2j\u0002\b3j\u0002\b4j\u0002\b5j\u0002\b6j\u0002\b7j\u0002\b8j\u0002\b9j\u0002\b:j\u0002\b;j\u0002\b<j\u0002\b=j\u0002\b>j\u0002\b?j\u0002\b@j\u0002\bAj\u0002\bBj\u0002\bCj\u0002\bDj\u0002\bEj\u0002\bFj\u0002\bGj\u0002\bHj\u0002\bIj\u0002\bJj\u0002\bKj\u0002\bLj\u0002\bMj\u0002\bNj\u0002\bOj\u0002\bPj\u0002\bQj\u0002\bRj\u0002\bSj\u0002\bTj\u0002\bUj\u0002\bVj\u0002\bWj\u0002\bXj\u0002\bYj\u0002\bZj\u0002\b[j\u0002\b\\j\u0002\b]j\u0002\b^j\u0002\b_j\u0002\b`j\u0002\baj\u0002\bbj\u0002\bcj\u0002\bdj\u0002\bej\u0002\bfj\u0002\bgj\u0002\bhj\u0002\bij\u0002\bjj\u0002\bkj\u0002\blj\u0002\bmj\u0002\bnj\u0002\boj\u0002\bpj\u0002\bqj\u0002\brj\u0002\bsj\u0002\btj\u0002\buj\u0002\bvj\u0002\bwj\u0002\bxj\u0002\byj\u0002\bzj\u0002\b{j\u0002\b|j\u0002\b}j\u0002\b~j\u0002\b\u007fj\u0003\b\u0080\u0001j\u0003\b\u0081\u0001j\u0003\b\u0082\u0001j\u0003\b\u0083\u0001j\u0003\b\u0084\u0001j\u0003\b\u0085\u0001j\u0003\b\u0086\u0001j\u0003\b\u0087\u0001j\u0003\b\u0088\u0001j\u0003\b\u0089\u0001j\u0003\b\u008a\u0001¨\u0006\u008b\u0001"}, d2 = {"Lcom/vk/stat/sak/scheme/SchemeStatSak$RegistrationFieldItem$Name;", "", "PHONE_STATE", "WRITE_CONTACTS", "COARSE_LOCATION", "PHONE_NUMBER", "CALL_LIST", "PHONE_BOOK", CodePackage.LOCATION, "PUSH", "SMS_CODE", "COUNTRY", "PHONE_COUNTRY", "RULES_ACCEPT", "CAPTCHA", "FIRST_NAME", "LAST_NAME", "FULL_NAME", "SEX", "BDAY", "PASSWORD", "PASSWORD_VERIFY", "PHOTO", "FRIEND_ASK", "AUTH_EXISTING_ACCOUNT_OPEN", "VERIFICATION_TYPE", "AUTH_FLOW_SOURCE", "PROMO_AUTH_FLAG", "EXTERNAL_ACCOUNTS_SHOWING", "EMAIL", "SELECT_COUNTRY_NAME", "IS_OLD_SERVICE_NUMBER", "ACCOUNT_FOUND_BY_NUMBER", "ACCOUNT_FOUND_SEAMLESSLY", "IS_NET_ERROR", "CONTENTS_AUTHS", "QR_CODE_ID", "QR_CODE_SOURCE", "APP_ID", "AUTH_CODE_ID", "VERIFICATION_FACTOR_NUMBER", "VERIFICATION_FLOW", "ACCOUNTS_CNT", "ACCOUNTS_IDS", "LINK_TYPE", "OAUTH_SERVICE", "ESIA_AWAY", "VERIFICATION_STATUS", "LEAVE_UNCHANGED", "ESIA_SYNCHRONIZED_DATA", "CLOSE_TAB", "CAN_SKIP", "FROM_POPUP", "VERIFICATION_OAUTH", "TO_SWITCHER_FROM", "LOGOUT_REASON", "ONBOARDING_TYPE", "ONBOARDED", FileTypes.SOURCE, "DEEPLINK", "USECASE", "USECASE_EXPLANATION", "REG_ADD_TYPE", "PASSKEY", "ECOSYSTEM_PUSH", "SMS", "CALL_RESET", GrsBaseInfo.CountryCodeSource.APP, "RESERVE_CODE", "OFFICIAL_MESSENGER", "VALIDATION_FACTOR_FLOW", "CALLIN_ERROR_TEXT", "REASON", "EVENT_DURATION", "AUTOLOGIN_ID", "AVAILABLE_MULTIACC_SELECTOR", "OAUTH_NAME", "REG_FLOW", "ALERT", "UNIQUE_SESSION_ID", "FROM", "ENV", "MINI_APP_ID", "MINI_APP_TYPE", "METHOD_NAME", "AVAILABLE_REG", "TYPE_CAROUSEL", "VKME_FLOW_TYPE", "BACKUP", "COUNT", "MAIL_RU", "OK_RU", "YANDEX", "ESIA", "TINKOFF", "SBER", "ALFA", "GOOGLE", "APPLE_ID", "IS_ACTIVE_PROFILE", "CAN_SKIP_AUTH", "IS_INPUT_SKIPPED_BY_EMAIL", "CAN_ENTER_BY_MAIL_PASS", "GROUP_ID", "PROFILE_TYPE", "TO_PROFILE_TYPE", "FROM_PROFILE_TYPE", "HAS_ACCESS_TOKEN", "ENTRY_POINT", Statistic.RESTORE_TYPE, "IS_ACTIVE_SESSION", "WEBVIEW_UNAUTH_ID", "IS_NOT_MY_VKID_ENABLE", "ERROR_DESCRIPTION", "IS_PHONE_LINKED", "RESTORE_REASON", "PASSWORD_AUTOFILL", "APP_MARKET_NAME", "GRAY_OAUTH", "MAX_MESSENGER", "INSTALLED_APPS", "ERROR_CODE", "MAIL_SCREEN_TYPE", "REG_SOURCE", "BLOCK_REASON", "MAIN_SCREEN_TYPE", "OAUTH_VKID", "OAUTH_ESIA", "OAUTH_GOOGLE", "OAUTH_YANDEX", "OAUTH_APPLE", "OAUTH_SBER", "CHOOSEN_DOMAIN", "ACCOUNT_IDS", "REDIRECT_REASON", "SCREEN_TYPE", "MAX_CODE", "PHONE_COUNTRY_CODE", "MAIL_DEVICE_ID", "sak_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
        public static final class Name {
            PHONE_STATE,
            WRITE_CONTACTS,
            COARSE_LOCATION,
            PHONE_NUMBER,
            CALL_LIST,
            PHONE_BOOK,
            LOCATION,
            PUSH,
            SMS_CODE,
            COUNTRY,
            PHONE_COUNTRY,
            RULES_ACCEPT,
            CAPTCHA,
            FIRST_NAME,
            LAST_NAME,
            FULL_NAME,
            SEX,
            BDAY,
            PASSWORD,
            PASSWORD_VERIFY,
            PHOTO,
            FRIEND_ASK,
            AUTH_EXISTING_ACCOUNT_OPEN,
            VERIFICATION_TYPE,
            AUTH_FLOW_SOURCE,
            PROMO_AUTH_FLAG,
            EXTERNAL_ACCOUNTS_SHOWING,
            EMAIL,
            SELECT_COUNTRY_NAME,
            IS_OLD_SERVICE_NUMBER,
            ACCOUNT_FOUND_BY_NUMBER,
            ACCOUNT_FOUND_SEAMLESSLY,
            IS_NET_ERROR,
            CONTENTS_AUTHS,
            QR_CODE_ID,
            QR_CODE_SOURCE,
            APP_ID,
            AUTH_CODE_ID,
            VERIFICATION_FACTOR_NUMBER,
            VERIFICATION_FLOW,
            ACCOUNTS_CNT,
            ACCOUNTS_IDS,
            LINK_TYPE,
            OAUTH_SERVICE,
            ESIA_AWAY,
            VERIFICATION_STATUS,
            LEAVE_UNCHANGED,
            ESIA_SYNCHRONIZED_DATA,
            CLOSE_TAB,
            CAN_SKIP,
            FROM_POPUP,
            VERIFICATION_OAUTH,
            TO_SWITCHER_FROM,
            LOGOUT_REASON,
            ONBOARDING_TYPE,
            ONBOARDED,
            SOURCE,
            DEEPLINK,
            USECASE,
            USECASE_EXPLANATION,
            REG_ADD_TYPE,
            PASSKEY,
            ECOSYSTEM_PUSH,
            SMS,
            CALL_RESET,
            APP,
            RESERVE_CODE,
            OFFICIAL_MESSENGER,
            VALIDATION_FACTOR_FLOW,
            CALLIN_ERROR_TEXT,
            REASON,
            EVENT_DURATION,
            AUTOLOGIN_ID,
            AVAILABLE_MULTIACC_SELECTOR,
            OAUTH_NAME,
            REG_FLOW,
            ALERT,
            UNIQUE_SESSION_ID,
            FROM,
            ENV,
            MINI_APP_ID,
            MINI_APP_TYPE,
            METHOD_NAME,
            AVAILABLE_REG,
            TYPE_CAROUSEL,
            VKME_FLOW_TYPE,
            BACKUP,
            COUNT,
            MAIL_RU,
            OK_RU,
            YANDEX,
            ESIA,
            TINKOFF,
            SBER,
            ALFA,
            GOOGLE,
            APPLE_ID,
            IS_ACTIVE_PROFILE,
            CAN_SKIP_AUTH,
            IS_INPUT_SKIPPED_BY_EMAIL,
            CAN_ENTER_BY_MAIL_PASS,
            GROUP_ID,
            PROFILE_TYPE,
            TO_PROFILE_TYPE,
            FROM_PROFILE_TYPE,
            HAS_ACCESS_TOKEN,
            ENTRY_POINT,
            RESTORE_TYPE,
            IS_ACTIVE_SESSION,
            WEBVIEW_UNAUTH_ID,
            IS_NOT_MY_VKID_ENABLE,
            ERROR_DESCRIPTION,
            IS_PHONE_LINKED,
            RESTORE_REASON,
            PASSWORD_AUTOFILL,
            APP_MARKET_NAME,
            GRAY_OAUTH,
            MAX_MESSENGER,
            INSTALLED_APPS,
            ERROR_CODE,
            MAIL_SCREEN_TYPE,
            REG_SOURCE,
            BLOCK_REASON,
            MAIN_SCREEN_TYPE,
            OAUTH_VKID,
            OAUTH_ESIA,
            OAUTH_GOOGLE,
            OAUTH_YANDEX,
            OAUTH_APPLE,
            OAUTH_SBER,
            CHOOSEN_DOMAIN,
            ACCOUNT_IDS,
            REDIRECT_REASON,
            SCREEN_TYPE,
            MAX_CODE,
            PHONE_COUNTRY_CODE,
            MAIL_DEVICE_ID;

            private static final /* synthetic */ EnumEntries kastatsbilkvmocb;

            static {
                kastatsbilkvmocb = EnumEntriesKt.enumEntries(nameArr);
            }

            private Name() {
                super(str, i);
            }

            @NotNull
            public static EnumEntries<Name> getEntries() {
                return kastatsbilkvmocb;
            }

            public static Name valueOf(String str) {
                return (Name) Enum.valueOf(Name.class, str);
            }

            public static Name[] values() {
                return (Name[]) kastatsbilkvmoca.clone();
            }
        }

        public RegistrationFieldItem(@NotNull Name name, @NotNull String startInteractionTime, @NotNull String endInteractionTime, @Nullable String str) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(startInteractionTime, "startInteractionTime");
            Intrinsics.checkNotNullParameter(endInteractionTime, "endInteractionTime");
            this.name = name;
            this.startInteractionTime = startInteractionTime;
            this.endInteractionTime = endInteractionTime;
            this.value = str;
        }

        public static /* synthetic */ RegistrationFieldItem copy$default(RegistrationFieldItem registrationFieldItem, Name name, String str, String str2, String str3, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                name = registrationFieldItem.name;
            }
            if ((i10 & 2) != 0) {
                str = registrationFieldItem.startInteractionTime;
            }
            if ((i10 & 4) != 0) {
                str2 = registrationFieldItem.endInteractionTime;
            }
            if ((i10 & 8) != 0) {
                str3 = registrationFieldItem.value;
            }
            return registrationFieldItem.copy(name, str, str2, str3);
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Name getName() {
            return this.name;
        }

        @NotNull
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getStartInteractionTime() {
            return this.startInteractionTime;
        }

        @NotNull
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getEndInteractionTime() {
            return this.endInteractionTime;
        }

        @Nullable
        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getValue() {
            return this.value;
        }

        @NotNull
        public final RegistrationFieldItem copy(@NotNull Name name, @NotNull String startInteractionTime, @NotNull String endInteractionTime, @Nullable String value) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(startInteractionTime, "startInteractionTime");
            Intrinsics.checkNotNullParameter(endInteractionTime, "endInteractionTime");
            return new RegistrationFieldItem(name, startInteractionTime, endInteractionTime, value);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof RegistrationFieldItem)) {
                return false;
            }
            RegistrationFieldItem registrationFieldItem = (RegistrationFieldItem) other;
            return this.name == registrationFieldItem.name && Intrinsics.areEqual(this.startInteractionTime, registrationFieldItem.startInteractionTime) && Intrinsics.areEqual(this.endInteractionTime, registrationFieldItem.endInteractionTime) && Intrinsics.areEqual(this.value, registrationFieldItem.value);
        }

        @NotNull
        public final String getEndInteractionTime() {
            return this.endInteractionTime;
        }

        @NotNull
        public final Name getName() {
            return this.name;
        }

        @NotNull
        public final String getStartInteractionTime() {
            return this.startInteractionTime;
        }

        @Nullable
        public final String getValue() {
            return this.value;
        }

        public int hashCode() {
            int iHashCode = (this.endInteractionTime.hashCode() + ((this.startInteractionTime.hashCode() + (this.name.hashCode() * 31)) * 31)) * 31;
            String str = this.value;
            return iHashCode + (str == null ? 0 : str.hashCode());
        }

        @NotNull
        public String toString() {
            return "RegistrationFieldItem(name=" + this.name + ", startInteractionTime=" + this.startInteractionTime + ", endInteractionTime=" + this.endInteractionTime + ", value=" + this.value + ')';
        }

        public /* synthetic */ RegistrationFieldItem(Name name, String str, String str2, String str3, int i10, DefaultConstructorMarker defaultConstructorMarker) {
            this(name, str, str2, (i10 & 8) != 0 ? null : str3);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes12.dex */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\r\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\r\b\u0086\b\u0018\u00002\u00020\u0001B3\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\n\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\n\u0010\u000bJ\u0012\u0010\f\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\f\u0010\u000bJ\u0012\u0010\r\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0004\b\r\u0010\u000eJ\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u000f\u0010\u000bJ>\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0002HÆ\u0001¢\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0012\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u0012\u0010\u000bJ\u0010\u0010\u0014\u001a\u00020\u0013HÖ\u0001¢\u0006\u0004\b\u0014\u0010\u0015J\u001a\u0010\u0019\u001a\u00020\u00182\b\u0010\u0017\u001a\u0004\u0018\u00010\u0016HÖ\u0003¢\u0006\u0004\b\u0019\u0010\u001aR\u001a\u0010\u0003\u001a\u00020\u00028\u0006X\u0087\u0004¢\u0006\f\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\u000bR\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u00028\u0006X\u0087\u0004¢\u0006\f\n\u0004\b\u001e\u0010\u001c\u001a\u0004\b\u001f\u0010\u000bR\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\f\n\u0004\b \u0010!\u001a\u0004\b\"\u0010\u000eR\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u00028\u0006X\u0087\u0004¢\u0006\f\n\u0004\b#\u0010\u001c\u001a\u0004\b$\u0010\u000b¨\u0006%"}, d2 = {"Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeDebugStatsItem;", "Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeAction$Payload;", "", "eventType", "description", "", "descriptionNumeric", "json", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Float;Ljava/lang/String;)V", "component1", "()Ljava/lang/String;", "component2", "component3", "()Ljava/lang/Float;", "component4", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Float;Ljava/lang/String;)Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeDebugStatsItem;", "toString", "", "hashCode", "()I", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "kastatsbilkvmoca", "Ljava/lang/String;", "getEventType", "kastatsbilkvmocb", "getDescription", "kastatsbilkvmocc", "Ljava/lang/Float;", "getDescriptionNumeric", "kastatsbilkvmocd", "getJson", "sak_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final /* data */ class TypeDebugStatsItem implements TypeAction.Payload {

        /* JADX INFO: renamed from: kastatsbilkvmoca, reason: from kotlin metadata */
        @SerializedName(MailContract.EVENT_TYPE)
        @NotNull
        private final String eventType;

        /* JADX INFO: renamed from: kastatsbilkvmocb, reason: from kotlin metadata */
        @SerializedName("description")
        @Nullable
        private final String description;

        /* JADX INFO: renamed from: kastatsbilkvmocc, reason: from kotlin metadata */
        @SerializedName("description_numeric")
        @Nullable
        private final Float descriptionNumeric;

        /* JADX INFO: renamed from: kastatsbilkvmocd, reason: from kotlin metadata */
        @SerializedName("json")
        @Nullable
        private final String json;

        public TypeDebugStatsItem(@NotNull String eventType, @Nullable String str, @Nullable Float f10, @Nullable String str2) {
            Intrinsics.checkNotNullParameter(eventType, "eventType");
            this.eventType = eventType;
            this.description = str;
            this.descriptionNumeric = f10;
            this.json = str2;
        }

        public static /* synthetic */ TypeDebugStatsItem copy$default(TypeDebugStatsItem typeDebugStatsItem, String str, String str2, Float f10, String str3, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = typeDebugStatsItem.eventType;
            }
            if ((i10 & 2) != 0) {
                str2 = typeDebugStatsItem.description;
            }
            if ((i10 & 4) != 0) {
                f10 = typeDebugStatsItem.descriptionNumeric;
            }
            if ((i10 & 8) != 0) {
                str3 = typeDebugStatsItem.json;
            }
            return typeDebugStatsItem.copy(str, str2, f10, str3);
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getEventType() {
            return this.eventType;
        }

        @Nullable
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getDescription() {
            return this.description;
        }

        @Nullable
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final Float getDescriptionNumeric() {
            return this.descriptionNumeric;
        }

        @Nullable
        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getJson() {
            return this.json;
        }

        @NotNull
        public final TypeDebugStatsItem copy(@NotNull String eventType, @Nullable String description, @Nullable Float descriptionNumeric, @Nullable String json) {
            Intrinsics.checkNotNullParameter(eventType, "eventType");
            return new TypeDebugStatsItem(eventType, description, descriptionNumeric, json);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof TypeDebugStatsItem)) {
                return false;
            }
            TypeDebugStatsItem typeDebugStatsItem = (TypeDebugStatsItem) other;
            return Intrinsics.areEqual(this.eventType, typeDebugStatsItem.eventType) && Intrinsics.areEqual(this.description, typeDebugStatsItem.description) && Intrinsics.areEqual((Object) this.descriptionNumeric, (Object) typeDebugStatsItem.descriptionNumeric) && Intrinsics.areEqual(this.json, typeDebugStatsItem.json);
        }

        @Nullable
        public final String getDescription() {
            return this.description;
        }

        @Nullable
        public final Float getDescriptionNumeric() {
            return this.descriptionNumeric;
        }

        @NotNull
        public final String getEventType() {
            return this.eventType;
        }

        @Nullable
        public final String getJson() {
            return this.json;
        }

        public int hashCode() {
            int iHashCode = this.eventType.hashCode() * 31;
            String str = this.description;
            int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
            Float f10 = this.descriptionNumeric;
            int iHashCode3 = (iHashCode2 + (f10 == null ? 0 : f10.hashCode())) * 31;
            String str2 = this.json;
            return iHashCode3 + (str2 != null ? str2.hashCode() : 0);
        }

        @NotNull
        public String toString() {
            return "TypeDebugStatsItem(eventType=" + this.eventType + ", description=" + this.description + ", descriptionNumeric=" + this.descriptionNumeric + ", json=" + this.json + ')';
        }

        public /* synthetic */ TypeDebugStatsItem(String str, String str2, Float f10, String str3, int i10, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (i10 & 2) != 0 ? null : str2, (i10 & 4) != 0 ? null : f10, (i10 & 8) != 0 ? null : str3);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes12.dex */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u0000\n\u0002\b\u001a\b\u0086\b\u0018\u00002\u00020\u0001:\u0001>B]\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0004\u0012\u0010\b\u0002\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000e¢\u0006\u0004\b\u0011\u0010\u0012J\u0010\u0010\u0013\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0013\u0010\u0014J\u0010\u0010\u0015\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b\u0015\u0010\u0016J\u0010\u0010\u0017\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b\u0017\u0010\u0016J\u0010\u0010\u0018\u001a\u00020\u0007HÆ\u0003¢\u0006\u0004\b\u0018\u0010\u0019J\u0012\u0010\u001a\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0004\b\u001a\u0010\u001bJ\u0012\u0010\u001c\u001a\u0004\u0018\u00010\u000bHÆ\u0003¢\u0006\u0004\b\u001c\u0010\u001dJ\u0012\u0010\u001e\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0004\b\u001e\u0010\u0016J\u0018\u0010\u001f\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000eHÆ\u0003¢\u0006\u0004\b\u001f\u0010 Jn\u0010!\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u00072\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00042\u0010\b\u0002\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000eHÆ\u0001¢\u0006\u0004\b!\u0010\"J\u0010\u0010#\u001a\u00020\u0004HÖ\u0001¢\u0006\u0004\b#\u0010\u0016J\u0010\u0010$\u001a\u00020\u0007HÖ\u0001¢\u0006\u0004\b$\u0010\u0019J\u001a\u0010'\u001a\u00020\t2\b\u0010&\u001a\u0004\u0018\u00010%HÖ\u0003¢\u0006\u0004\b'\u0010(R\u001a\u0010\u0003\u001a\u00020\u00028\u0006X\u0087\u0004¢\u0006\f\n\u0004\b)\u0010*\u001a\u0004\b+\u0010\u0014R\u001a\u0010\u0005\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\f\n\u0004\b,\u0010-\u001a\u0004\b.\u0010\u0016R\u001a\u0010\u0006\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\f\n\u0004\b/\u0010-\u001a\u0004\b0\u0010\u0016R\u001a\u0010\b\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\f\n\u0004\b1\u00102\u001a\u0004\b3\u0010\u0019R\u001c\u0010\n\u001a\u0004\u0018\u00010\t8\u0006X\u0087\u0004¢\u0006\f\n\u0004\b4\u00105\u001a\u0004\b\n\u0010\u001bR\u001c\u0010\f\u001a\u0004\u0018\u00010\u000b8\u0006X\u0087\u0004¢\u0006\f\n\u0004\b6\u00107\u001a\u0004\b8\u0010\u001dR\u001c\u0010\r\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004¢\u0006\f\n\u0004\b9\u0010-\u001a\u0004\b:\u0010\u0016R\"\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000e8\u0006X\u0087\u0004¢\u0006\f\n\u0004\b;\u0010<\u001a\u0004\b=\u0010 ¨\u0006?"}, d2 = {"Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeSakSessionsEventItem;", "Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeAction$Payload;", "Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeSakSessionsEventItem$Step;", "step", "", "sakVersion", "packageName", "", "appId", "", "isFirstSession", "", BlockstoreDeleteReceiver.PARAM_USER_ID, "unauthId", "", "Lcom/vk/stat/sak/scheme/SchemeStatSak$SakSessionsEventFieldItem;", "fields", "<init>", "(Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeSakSessionsEventItem$Step;Ljava/lang/String;Ljava/lang/String;ILjava/lang/Boolean;Ljava/lang/Long;Ljava/lang/String;Ljava/util/List;)V", "component1", "()Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeSakSessionsEventItem$Step;", "component2", "()Ljava/lang/String;", "component3", "component4", "()I", "component5", "()Ljava/lang/Boolean;", "component6", "()Ljava/lang/Long;", "component7", "component8", "()Ljava/util/List;", "copy", "(Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeSakSessionsEventItem$Step;Ljava/lang/String;Ljava/lang/String;ILjava/lang/Boolean;Ljava/lang/Long;Ljava/lang/String;Ljava/util/List;)Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeSakSessionsEventItem;", "toString", "hashCode", "", "other", "equals", "(Ljava/lang/Object;)Z", "kastatsbilkvmoca", "Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeSakSessionsEventItem$Step;", "getStep", "kastatsbilkvmocb", "Ljava/lang/String;", "getSakVersion", "kastatsbilkvmocc", "getPackageName", "kastatsbilkvmocd", "I", "getAppId", "kastatsbilkvmoce", "Ljava/lang/Boolean;", "kastatsbilkvmocf", "Ljava/lang/Long;", "getUserId", "kastatsbilkvmocg", "getUnauthId", "kastatsbilkvmoch", "Ljava/util/List;", "getFields", "Step", "sak_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final /* data */ class TypeSakSessionsEventItem implements TypeAction.Payload {

        /* JADX INFO: renamed from: kastatsbilkvmoca, reason: from kotlin metadata */
        @SerializedName("step")
        @NotNull
        private final Step step;

        /* JADX INFO: renamed from: kastatsbilkvmocb, reason: from kotlin metadata */
        @SerializedName(RegistrationStatParamsFactory.SAK_VERSION)
        @NotNull
        private final String sakVersion;

        /* JADX INFO: renamed from: kastatsbilkvmocc, reason: from kotlin metadata */
        @SerializedName(AnalyticsBaseParamsConstantsKt.PACKAGE_NAME)
        @NotNull
        private final String packageName;

        /* JADX INFO: renamed from: kastatsbilkvmocd, reason: from kotlin metadata */
        @SerializedName("app_id")
        private final int appId;

        /* JADX INFO: renamed from: kastatsbilkvmoce, reason: from kotlin metadata */
        @SerializedName("is_first_session")
        @Nullable
        private final Boolean isFirstSession;

        /* JADX INFO: renamed from: kastatsbilkvmocf, reason: from kotlin metadata */
        @SerializedName("user_id")
        @Nullable
        private final Long userId;

        /* JADX INFO: renamed from: kastatsbilkvmocg, reason: from kotlin metadata */
        @SerializedName("unauth_id")
        @Nullable
        private final String unauthId;

        /* JADX INFO: renamed from: kastatsbilkvmoch, reason: from kotlin metadata */
        @SerializedName("fields")
        @Nullable
        private final List<SakSessionsEventFieldItem> fields;

        /* JADX WARN: Enum visitor error
        jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v1 com.vk.stat.sak.scheme.SchemeStatSak$TypeSakSessionsEventItem$Step[], still in use, count: 1, list:
          (r0v1 com.vk.stat.sak.scheme.SchemeStatSak$TypeSakSessionsEventItem$Step[]) from 0x0024: INVOKE (r0v1 com.vk.stat.sak.scheme.SchemeStatSak$TypeSakSessionsEventItem$Step[]) STATIC call: kotlin.enums.EnumEntriesKt.enumEntries(java.lang.Enum[]):kotlin.enums.EnumEntries A[MD:<E extends java.lang.Enum<E>>:(E extends java.lang.Enum<E>[]):kotlin.enums.EnumEntries<E extends java.lang.Enum<E>> (m), WRAPPED] (LINE:37)
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
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001j\u0002\b\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, d2 = {"Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeSakSessionsEventItem$Step;", "", "INIT_SAK", "START_SESSION", "COMPLETE_SESSION", "sak_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
        public static final class Step {
            INIT_SAK,
            START_SESSION,
            COMPLETE_SESSION;

            private static final /* synthetic */ EnumEntries kastatsbilkvmocb;

            static {
                kastatsbilkvmocb = EnumEntriesKt.enumEntries(stepArr);
            }

            private Step() {
                super(str, i);
            }

            @NotNull
            public static EnumEntries<Step> getEntries() {
                return kastatsbilkvmocb;
            }

            public static Step valueOf(String str) {
                return (Step) Enum.valueOf(Step.class, str);
            }

            public static Step[] values() {
                return (Step[]) kastatsbilkvmoca.clone();
            }
        }

        public TypeSakSessionsEventItem(@NotNull Step step, @NotNull String sakVersion, @NotNull String packageName, int i10, @Nullable Boolean bool, @Nullable Long l10, @Nullable String str, @Nullable List<SakSessionsEventFieldItem> list) {
            Intrinsics.checkNotNullParameter(step, "step");
            Intrinsics.checkNotNullParameter(sakVersion, "sakVersion");
            Intrinsics.checkNotNullParameter(packageName, "packageName");
            this.step = step;
            this.sakVersion = sakVersion;
            this.packageName = packageName;
            this.appId = i10;
            this.isFirstSession = bool;
            this.userId = l10;
            this.unauthId = str;
            this.fields = list;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ TypeSakSessionsEventItem copy$default(TypeSakSessionsEventItem typeSakSessionsEventItem, Step step, String str, String str2, int i10, Boolean bool, Long l10, String str3, List list, int i11, Object obj) {
            if ((i11 & 1) != 0) {
                step = typeSakSessionsEventItem.step;
            }
            if ((i11 & 2) != 0) {
                str = typeSakSessionsEventItem.sakVersion;
            }
            if ((i11 & 4) != 0) {
                str2 = typeSakSessionsEventItem.packageName;
            }
            if ((i11 & 8) != 0) {
                i10 = typeSakSessionsEventItem.appId;
            }
            if ((i11 & 16) != 0) {
                bool = typeSakSessionsEventItem.isFirstSession;
            }
            if ((i11 & 32) != 0) {
                l10 = typeSakSessionsEventItem.userId;
            }
            if ((i11 & 64) != 0) {
                str3 = typeSakSessionsEventItem.unauthId;
            }
            if ((i11 & 128) != 0) {
                list = typeSakSessionsEventItem.fields;
            }
            String str4 = str3;
            List list2 = list;
            Boolean bool2 = bool;
            Long l11 = l10;
            return typeSakSessionsEventItem.copy(step, str, str2, i10, bool2, l11, str4, list2);
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Step getStep() {
            return this.step;
        }

        @NotNull
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getSakVersion() {
            return this.sakVersion;
        }

        @NotNull
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getPackageName() {
            return this.packageName;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final int getAppId() {
            return this.appId;
        }

        @Nullable
        /* JADX INFO: renamed from: component5, reason: from getter */
        public final Boolean getIsFirstSession() {
            return this.isFirstSession;
        }

        @Nullable
        /* JADX INFO: renamed from: component6, reason: from getter */
        public final Long getUserId() {
            return this.userId;
        }

        @Nullable
        /* JADX INFO: renamed from: component7, reason: from getter */
        public final String getUnauthId() {
            return this.unauthId;
        }

        @Nullable
        public final List<SakSessionsEventFieldItem> component8() {
            return this.fields;
        }

        @NotNull
        public final TypeSakSessionsEventItem copy(@NotNull Step step, @NotNull String sakVersion, @NotNull String packageName, int appId, @Nullable Boolean isFirstSession, @Nullable Long userId, @Nullable String unauthId, @Nullable List<SakSessionsEventFieldItem> fields) {
            Intrinsics.checkNotNullParameter(step, "step");
            Intrinsics.checkNotNullParameter(sakVersion, "sakVersion");
            Intrinsics.checkNotNullParameter(packageName, "packageName");
            return new TypeSakSessionsEventItem(step, sakVersion, packageName, appId, isFirstSession, userId, unauthId, fields);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof TypeSakSessionsEventItem)) {
                return false;
            }
            TypeSakSessionsEventItem typeSakSessionsEventItem = (TypeSakSessionsEventItem) other;
            return this.step == typeSakSessionsEventItem.step && Intrinsics.areEqual(this.sakVersion, typeSakSessionsEventItem.sakVersion) && Intrinsics.areEqual(this.packageName, typeSakSessionsEventItem.packageName) && this.appId == typeSakSessionsEventItem.appId && Intrinsics.areEqual(this.isFirstSession, typeSakSessionsEventItem.isFirstSession) && Intrinsics.areEqual(this.userId, typeSakSessionsEventItem.userId) && Intrinsics.areEqual(this.unauthId, typeSakSessionsEventItem.unauthId) && Intrinsics.areEqual(this.fields, typeSakSessionsEventItem.fields);
        }

        public final int getAppId() {
            return this.appId;
        }

        @Nullable
        public final List<SakSessionsEventFieldItem> getFields() {
            return this.fields;
        }

        @NotNull
        public final String getPackageName() {
            return this.packageName;
        }

        @NotNull
        public final String getSakVersion() {
            return this.sakVersion;
        }

        @NotNull
        public final Step getStep() {
            return this.step;
        }

        @Nullable
        public final String getUnauthId() {
            return this.unauthId;
        }

        @Nullable
        public final Long getUserId() {
            return this.userId;
        }

        public int hashCode() {
            int iHashCode = (Integer.hashCode(this.appId) + ((this.packageName.hashCode() + ((this.sakVersion.hashCode() + (this.step.hashCode() * 31)) * 31)) * 31)) * 31;
            Boolean bool = this.isFirstSession;
            int iHashCode2 = (iHashCode + (bool == null ? 0 : bool.hashCode())) * 31;
            Long l10 = this.userId;
            int iHashCode3 = (iHashCode2 + (l10 == null ? 0 : l10.hashCode())) * 31;
            String str = this.unauthId;
            int iHashCode4 = (iHashCode3 + (str == null ? 0 : str.hashCode())) * 31;
            List<SakSessionsEventFieldItem> list = this.fields;
            return iHashCode4 + (list != null ? list.hashCode() : 0);
        }

        @Nullable
        public final Boolean isFirstSession() {
            return this.isFirstSession;
        }

        @NotNull
        public String toString() {
            return "TypeSakSessionsEventItem(step=" + this.step + ", sakVersion=" + this.sakVersion + ", packageName=" + this.packageName + ", appId=" + this.appId + ", isFirstSession=" + this.isFirstSession + ", userId=" + this.userId + ", unauthId=" + this.unauthId + ", fields=" + this.fields + ')';
        }

        public /* synthetic */ TypeSakSessionsEventItem(Step step, String str, String str2, int i10, Boolean bool, Long l10, String str3, List list, int i11, DefaultConstructorMarker defaultConstructorMarker) {
            this(step, str, str2, i10, (i11 & 16) != 0 ? null : bool, (i11 & 32) != 0 ? null : l10, (i11 & 64) != 0 ? null : str3, (i11 & 128) != 0 ? null : list);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes12.dex */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0019\b\u0086\b\u0018\u00002\u00020\u0001:\u0001>Bi\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n\u0012\u0010\b\u0002\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\f\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0012\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0012\u0010\u0013J\u0012\u0010\u0014\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0004\b\u0014\u0010\u0015J\u0012\u0010\u0016\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0004\b\u0016\u0010\u0015J\u0012\u0010\u0017\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0004\b\u0017\u0010\u0018J\u0012\u0010\u0019\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0004\b\u0019\u0010\u0018J\u0012\u0010\u001a\u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0004\b\u001a\u0010\u001bJ\u0018\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\fHÆ\u0003¢\u0006\u0004\b\u001c\u0010\u001dJ\u0012\u0010\u001e\u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0004\b\u001e\u0010\u001bJt\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n2\u0010\b\u0002\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\f2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\nHÆ\u0001¢\u0006\u0004\b\u001f\u0010 J\u0010\u0010!\u001a\u00020\u0007HÖ\u0001¢\u0006\u0004\b!\u0010\u0018J\u0010\u0010\"\u001a\u00020\u0004HÖ\u0001¢\u0006\u0004\b\"\u0010#J\u001a\u0010'\u001a\u00020&2\b\u0010%\u001a\u0004\u0018\u00010$HÖ\u0003¢\u0006\u0004\b'\u0010(R\u001a\u0010\u0003\u001a\u00020\u00028\u0006X\u0087\u0004¢\u0006\f\n\u0004\b)\u0010*\u001a\u0004\b+\u0010\u0013R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004¢\u0006\f\n\u0004\b,\u0010-\u001a\u0004\b.\u0010\u0015R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004¢\u0006\f\n\u0004\b/\u0010-\u001a\u0004\b0\u0010\u0015R\u001c\u0010\b\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\f\n\u0004\b1\u00102\u001a\u0004\b3\u0010\u0018R\u001c\u0010\t\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\f\n\u0004\b4\u00102\u001a\u0004\b5\u0010\u0018R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\n8\u0006X\u0087\u0004¢\u0006\f\n\u0004\b6\u00107\u001a\u0004\b8\u0010\u001bR\"\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\f8\u0006X\u0087\u0004¢\u0006\f\n\u0004\b9\u0010:\u001a\u0004\b;\u0010\u001dR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\n8\u0006X\u0087\u0004¢\u0006\f\n\u0004\b<\u00107\u001a\u0004\b=\u0010\u001b¨\u0006?"}, d2 = {"Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeVkConnectNavigationItem;", "Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeAction$Payload;", "Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeVkConnectNavigationItem$EventType;", "eventType", "", "unauthId", "authAppId", "", "flowService", "flowType", "Lcom/vk/stat/sak/scheme/SchemeStatSak$EventScreen;", "screen", "", "Lcom/vk/stat/sak/scheme/SchemeStatSak$NavigationFieldItem;", "fields", "screenTo", "<init>", "(Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeVkConnectNavigationItem$EventType;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Lcom/vk/stat/sak/scheme/SchemeStatSak$EventScreen;Ljava/util/List;Lcom/vk/stat/sak/scheme/SchemeStatSak$EventScreen;)V", "component1", "()Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeVkConnectNavigationItem$EventType;", "component2", "()Ljava/lang/Integer;", "component3", "component4", "()Ljava/lang/String;", "component5", "component6", "()Lcom/vk/stat/sak/scheme/SchemeStatSak$EventScreen;", "component7", "()Ljava/util/List;", "component8", "copy", "(Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeVkConnectNavigationItem$EventType;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Lcom/vk/stat/sak/scheme/SchemeStatSak$EventScreen;Ljava/util/List;Lcom/vk/stat/sak/scheme/SchemeStatSak$EventScreen;)Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeVkConnectNavigationItem;", "toString", "hashCode", "()I", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "kastatsbilkvmoca", "Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeVkConnectNavigationItem$EventType;", "getEventType", "kastatsbilkvmocb", "Ljava/lang/Integer;", "getUnauthId", "kastatsbilkvmocc", "getAuthAppId", "kastatsbilkvmocd", "Ljava/lang/String;", "getFlowService", "kastatsbilkvmoce", "getFlowType", "kastatsbilkvmocf", "Lcom/vk/stat/sak/scheme/SchemeStatSak$EventScreen;", "getScreen", "kastatsbilkvmocg", "Ljava/util/List;", "getFields", "kastatsbilkvmoch", "getScreenTo", "EventType", "sak_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final /* data */ class TypeVkConnectNavigationItem implements TypeAction.Payload {

        /* JADX INFO: renamed from: kastatsbilkvmoca, reason: from kotlin metadata */
        @SerializedName(MailContract.EVENT_TYPE)
        @NotNull
        private final EventType eventType;

        /* JADX INFO: renamed from: kastatsbilkvmocb, reason: from kotlin metadata */
        @SerializedName("unauth_id")
        @Nullable
        private final Integer unauthId;

        /* JADX INFO: renamed from: kastatsbilkvmocc, reason: from kotlin metadata */
        @SerializedName(RegistrationStatParamsFactory.AUTH_APP_ID)
        @Nullable
        private final Integer authAppId;

        /* JADX INFO: renamed from: kastatsbilkvmocd, reason: from kotlin metadata */
        @SerializedName("flow_service")
        @Nullable
        private final String flowService;

        /* JADX INFO: renamed from: kastatsbilkvmoce, reason: from kotlin metadata */
        @SerializedName(RegistrationStatParamsFactory.FLOW_TYPE)
        @Nullable
        private final String flowType;

        /* JADX INFO: renamed from: kastatsbilkvmocf, reason: from kotlin metadata */
        @SerializedName("screen")
        @Nullable
        private final EventScreen screen;

        /* JADX INFO: renamed from: kastatsbilkvmocg, reason: from kotlin metadata */
        @SerializedName("fields")
        @Nullable
        private final List<NavigationFieldItem> fields;

        /* JADX INFO: renamed from: kastatsbilkvmoch, reason: from kotlin metadata */
        @SerializedName("screen_to")
        @Nullable
        private final EventScreen screenTo;

        /* JADX WARN: Enum visitor error
        jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v64 com.vk.stat.sak.scheme.SchemeStatSak$TypeVkConnectNavigationItem$EventType[], still in use, count: 1, list:
          (r0v64 com.vk.stat.sak.scheme.SchemeStatSak$TypeVkConnectNavigationItem$EventType[]) from 0x0425: INVOKE (r0v64 com.vk.stat.sak.scheme.SchemeStatSak$TypeVkConnectNavigationItem$EventType[]) STATIC call: kotlin.enums.EnumEntriesKt.enumEntries(java.lang.Enum[]):kotlin.enums.EnumEntries A[MD:<E extends java.lang.Enum<E>>:(E extends java.lang.Enum<E>[]):kotlin.enums.EnumEntries<E extends java.lang.Enum<E>> (m), WRAPPED] (LINE:1062)
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
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\bB\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001dj\u0002\b\u001ej\u0002\b\u001fj\u0002\b j\u0002\b!j\u0002\b\"j\u0002\b#j\u0002\b$j\u0002\b%j\u0002\b&j\u0002\b'j\u0002\b(j\u0002\b)j\u0002\b*j\u0002\b+j\u0002\b,j\u0002\b-j\u0002\b.j\u0002\b/j\u0002\b0j\u0002\b1j\u0002\b2j\u0002\b3j\u0002\b4j\u0002\b5j\u0002\b6j\u0002\b7j\u0002\b8j\u0002\b9j\u0002\b:j\u0002\b;j\u0002\b<j\u0002\b=j\u0002\b>j\u0002\b?j\u0002\b@j\u0002\bAj\u0002\bB¨\u0006C"}, d2 = {"Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeVkConnectNavigationItem$EventType;", "", "Serializer", "GO", "BACK", "HIDE", "SHOW", "START", "CLOSE", "PUSH", "ERROR_VK_MAIL", "ERROR_WRONG_PWD", "ERROR_WRONG_MAIL", "AWAY", "ENTER_NOTIFY_TOGGLE_ON", "ENTER_NOTIFY_TOGGLE_OFF", "LOGOUT", "OPEN_VK", "CANT_USE_SHORT_NAME", "SAVE", "END_ALL_SESSIONS", "END_SESSION", "DELETE_TRUSTED_DEVICES", "DELETE_LINKED_DEVICES", "DELETE_APP_PASSWORD", "DELETE_AVATAR", "SUCCESS_NEW_PASSWORD", "SERVICES_BUSINESS_TOGGLE_ON_PERSONAL_RECOMMENDATIONS", "SERVICES_BUSINESS_TOGGLE_OFF_PERSONAL_RECOMMENDATIONS", "SERVICES_BUSINESS_TOGGLE_ON_CONSULTATIONS", "SERVICES_BUSINESS_TOGGLE_OFF_CONSULTATIONS", "SERVICES_BUSINESS_TOGGLE_ON_PROMOS", "SERVICES_BUSINESS_TOGGLE_OFF_PROMOS", "SERVICES_BUSINESS_TOGGLE_ON_POLLS", "SERVICES_BUSINESS_TOGGLE_OFF_POLLS", "SHOW_BAR_LK", "CLICK_ENTER_LK", "CLICK_VK_PAY", "CLICK_VK_COMBO", "SERVICE_NAVIGATION_CLICK", "SERVICE_NAVIGATION_OPEN", "SERVICE_NAVIGATION_CLOSE", "POPUP_OPEN", "POPUP_CLOSE", "CLOSE_ESIA_ERROR_TAB", "CLOSE_VERIFICATION_ERROR_TAB", "VERIFICATION_TRY_AGAIN", "SETTINGS_LOGOUT_SUCCESS", "CLEAR_CACHE", "CLEAR_CACHE_SHOW", "CLEAR_CACHE_SUCCESS", "CLEAR_CACHE_CANCEL", "ADD_ACCOUNT", "SWITCH_ACCOUNT", "LINK_AVAILABLE_MAIL_CLICK", "LINK_AVAILABLE_MAIL_PROMO_JUMP", "LINK_AVAILABLE_MAIL_CANCELLATION", "LINK_AVAILABLE_MAIL_CLOSE", "CHANGE_PASSWORD", "NOTIFICATION_SETUP", "NOTIFICATION_DISABLE", "CLICK_MENU", "START_ADDING_ACCOUNT", "SWITCH_TO_EXISTING_ACCOUNT", "UNBLOCK_PROTECT_ACCOUNT_SHOW", "UNBLOCK_PROTECT_ACCOUNT_CONNECT", "UNBLOCK_PROTECT_ACCOUNT_CANCELLATION", "sak_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
        public static final class EventType {
            GO("go"),
            BACK(ButtonDto.KEY_TYPE_BACK),
            HIDE("hide"),
            SHOW("show"),
            START("start"),
            CLOSE("close"),
            PUSH("push"),
            ERROR_VK_MAIL("error_vk_mail"),
            ERROR_WRONG_PWD("error_wrong_pwd"),
            ERROR_WRONG_MAIL("error_wrong_mail"),
            AWAY("away"),
            ENTER_NOTIFY_TOGGLE_ON("enter_notify_toggle_on"),
            ENTER_NOTIFY_TOGGLE_OFF("enter_notify_toggle_off"),
            LOGOUT("logout"),
            OPEN_VK("open_vk"),
            CANT_USE_SHORT_NAME("cant_use_short_name"),
            SAVE("save"),
            END_ALL_SESSIONS("end_all_sessions"),
            END_SESSION("end_session"),
            DELETE_TRUSTED_DEVICES("delete_trusted_devices"),
            DELETE_LINKED_DEVICES("delete_linked_devices"),
            DELETE_APP_PASSWORD("delete_app-password"),
            DELETE_AVATAR("delete_avatar"),
            SUCCESS_NEW_PASSWORD("success_new_password"),
            SERVICES_BUSINESS_TOGGLE_ON_PERSONAL_RECOMMENDATIONS("services_business_toggle_on_personal_recommendations"),
            SERVICES_BUSINESS_TOGGLE_OFF_PERSONAL_RECOMMENDATIONS("services_business_toggle_off_personal_recommendations"),
            SERVICES_BUSINESS_TOGGLE_ON_CONSULTATIONS("services_business_toggle_on_consultations"),
            SERVICES_BUSINESS_TOGGLE_OFF_CONSULTATIONS("services_business_toggle_off_consultations"),
            SERVICES_BUSINESS_TOGGLE_ON_PROMOS("services_business_toggle_on_promos"),
            SERVICES_BUSINESS_TOGGLE_OFF_PROMOS("services_business_toggle_off_promos"),
            SERVICES_BUSINESS_TOGGLE_ON_POLLS("services_business_toggle_on_polls"),
            SERVICES_BUSINESS_TOGGLE_OFF_POLLS("services_business_toggle_off_polls"),
            SHOW_BAR_LK("show_bar_lk"),
            CLICK_ENTER_LK("click_enter_lk"),
            CLICK_VK_PAY("click_vk_pay"),
            CLICK_VK_COMBO("click_vk_combo"),
            SERVICE_NAVIGATION_CLICK("service_navigation_click"),
            SERVICE_NAVIGATION_OPEN("service_navigation_open"),
            SERVICE_NAVIGATION_CLOSE("service_navigation_close"),
            POPUP_OPEN("popup_open"),
            POPUP_CLOSE("popup_close"),
            CLOSE_ESIA_ERROR_TAB("close_esia_error_tab"),
            CLOSE_VERIFICATION_ERROR_TAB("close_verification_error_tab"),
            VERIFICATION_TRY_AGAIN("verification_try_again"),
            SETTINGS_LOGOUT_SUCCESS("settings_logout_success"),
            CLEAR_CACHE("clear_cache"),
            CLEAR_CACHE_SHOW("clear_cache_show"),
            CLEAR_CACHE_SUCCESS("clear_cache_success"),
            CLEAR_CACHE_CANCEL("clear_cache_cancel"),
            ADD_ACCOUNT("add_account"),
            SWITCH_ACCOUNT("switch_account"),
            LINK_AVAILABLE_MAIL_CLICK("link_available_mail_click"),
            LINK_AVAILABLE_MAIL_PROMO_JUMP("link_available_mail_promo_jump"),
            LINK_AVAILABLE_MAIL_CANCELLATION("link_available_mail_cancellation"),
            LINK_AVAILABLE_MAIL_CLOSE("link_available_mail_close"),
            CHANGE_PASSWORD(NpcPromoSheetProvider.CHANGE_PASSWORD_CALLBACK),
            NOTIFICATION_SETUP("notification_setup"),
            NOTIFICATION_DISABLE("notification_disable"),
            CLICK_MENU("click_menu"),
            START_ADDING_ACCOUNT("start_adding_account"),
            SWITCH_TO_EXISTING_ACCOUNT("switch_to_existing_account"),
            UNBLOCK_PROTECT_ACCOUNT_SHOW("unblock_protect_account_show"),
            UNBLOCK_PROTECT_ACCOUNT_CONNECT("unblock_protect_account_connect"),
            UNBLOCK_PROTECT_ACCOUNT_CANCELLATION("unblock_protect_account_cancellation");

            private static final /* synthetic */ EnumEntries kastatsbilkvmocb;

            @NotNull
            private final String kastatsbilkvmocc;

            /* JADX INFO: compiled from: ProGuard */
            @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J&\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u00022\b\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016¨\u0006\f"}, d2 = {"Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeVkConnectNavigationItem$EventType$Serializer;", "Lcom/google/gson/JsonSerializer;", "Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeVkConnectNavigationItem$EventType;", "<init>", "()V", "serialize", "Lcom/google/gson/JsonElement;", "src", "typeOfSrc", "Ljava/lang/reflect/Type;", "context", "Lcom/google/gson/JsonSerializationContext;", "sak_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
            @SourceDebugExtension({"SMAP\nSchemeStatSak.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SchemeStatSak.kt\ncom/vk/stat/sak/scheme/SchemeStatSak$TypeVkConnectNavigationItem$EventType$Serializer\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,3265:1\n1#2:3266\n*E\n"})
            public static final class Serializer implements JsonSerializer<EventType> {
                @Override // com.google.gson.JsonSerializer
                @NotNull
                public JsonElement serialize(@Nullable EventType src, @Nullable Type typeOfSrc, @Nullable JsonSerializationContext context) {
                    if (src != null) {
                        return new JsonPrimitive(src.kastatsbilkvmocc);
                    }
                    JsonNull INSTANCE = JsonNull.INSTANCE;
                    Intrinsics.checkNotNullExpressionValue(INSTANCE, "INSTANCE");
                    return INSTANCE;
                }
            }

            static {
                kastatsbilkvmocb = EnumEntriesKt.enumEntries(eventTypeArr);
            }

            private EventType(String str) {
                super(str, i);
                this.kastatsbilkvmocc = str;
            }

            @NotNull
            public static EnumEntries<EventType> getEntries() {
                return kastatsbilkvmocb;
            }

            public static EventType valueOf(String str) {
                return (EventType) Enum.valueOf(EventType.class, str);
            }

            public static EventType[] values() {
                return (EventType[]) kastatsbilkvmoca.clone();
            }
        }

        public TypeVkConnectNavigationItem(@NotNull EventType eventType, @Nullable Integer num, @Nullable Integer num2, @Nullable String str, @Nullable String str2, @Nullable EventScreen eventScreen, @Nullable List<NavigationFieldItem> list, @Nullable EventScreen eventScreen2) {
            Intrinsics.checkNotNullParameter(eventType, "eventType");
            this.eventType = eventType;
            this.unauthId = num;
            this.authAppId = num2;
            this.flowService = str;
            this.flowType = str2;
            this.screen = eventScreen;
            this.fields = list;
            this.screenTo = eventScreen2;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ TypeVkConnectNavigationItem copy$default(TypeVkConnectNavigationItem typeVkConnectNavigationItem, EventType eventType, Integer num, Integer num2, String str, String str2, EventScreen eventScreen, List list, EventScreen eventScreen2, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                eventType = typeVkConnectNavigationItem.eventType;
            }
            if ((i10 & 2) != 0) {
                num = typeVkConnectNavigationItem.unauthId;
            }
            if ((i10 & 4) != 0) {
                num2 = typeVkConnectNavigationItem.authAppId;
            }
            if ((i10 & 8) != 0) {
                str = typeVkConnectNavigationItem.flowService;
            }
            if ((i10 & 16) != 0) {
                str2 = typeVkConnectNavigationItem.flowType;
            }
            if ((i10 & 32) != 0) {
                eventScreen = typeVkConnectNavigationItem.screen;
            }
            if ((i10 & 64) != 0) {
                list = typeVkConnectNavigationItem.fields;
            }
            if ((i10 & 128) != 0) {
                eventScreen2 = typeVkConnectNavigationItem.screenTo;
            }
            List list2 = list;
            EventScreen eventScreen3 = eventScreen2;
            String str3 = str2;
            EventScreen eventScreen4 = eventScreen;
            return typeVkConnectNavigationItem.copy(eventType, num, num2, str, str3, eventScreen4, list2, eventScreen3);
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final EventType getEventType() {
            return this.eventType;
        }

        @Nullable
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final Integer getUnauthId() {
            return this.unauthId;
        }

        @Nullable
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final Integer getAuthAppId() {
            return this.authAppId;
        }

        @Nullable
        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getFlowService() {
            return this.flowService;
        }

        @Nullable
        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getFlowType() {
            return this.flowType;
        }

        @Nullable
        /* JADX INFO: renamed from: component6, reason: from getter */
        public final EventScreen getScreen() {
            return this.screen;
        }

        @Nullable
        public final List<NavigationFieldItem> component7() {
            return this.fields;
        }

        @Nullable
        /* JADX INFO: renamed from: component8, reason: from getter */
        public final EventScreen getScreenTo() {
            return this.screenTo;
        }

        @NotNull
        public final TypeVkConnectNavigationItem copy(@NotNull EventType eventType, @Nullable Integer unauthId, @Nullable Integer authAppId, @Nullable String flowService, @Nullable String flowType, @Nullable EventScreen screen, @Nullable List<NavigationFieldItem> fields, @Nullable EventScreen screenTo) {
            Intrinsics.checkNotNullParameter(eventType, "eventType");
            return new TypeVkConnectNavigationItem(eventType, unauthId, authAppId, flowService, flowType, screen, fields, screenTo);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof TypeVkConnectNavigationItem)) {
                return false;
            }
            TypeVkConnectNavigationItem typeVkConnectNavigationItem = (TypeVkConnectNavigationItem) other;
            return this.eventType == typeVkConnectNavigationItem.eventType && Intrinsics.areEqual(this.unauthId, typeVkConnectNavigationItem.unauthId) && Intrinsics.areEqual(this.authAppId, typeVkConnectNavigationItem.authAppId) && Intrinsics.areEqual(this.flowService, typeVkConnectNavigationItem.flowService) && Intrinsics.areEqual(this.flowType, typeVkConnectNavigationItem.flowType) && this.screen == typeVkConnectNavigationItem.screen && Intrinsics.areEqual(this.fields, typeVkConnectNavigationItem.fields) && this.screenTo == typeVkConnectNavigationItem.screenTo;
        }

        @Nullable
        public final Integer getAuthAppId() {
            return this.authAppId;
        }

        @NotNull
        public final EventType getEventType() {
            return this.eventType;
        }

        @Nullable
        public final List<NavigationFieldItem> getFields() {
            return this.fields;
        }

        @Nullable
        public final String getFlowService() {
            return this.flowService;
        }

        @Nullable
        public final String getFlowType() {
            return this.flowType;
        }

        @Nullable
        public final EventScreen getScreen() {
            return this.screen;
        }

        @Nullable
        public final EventScreen getScreenTo() {
            return this.screenTo;
        }

        @Nullable
        public final Integer getUnauthId() {
            return this.unauthId;
        }

        public int hashCode() {
            int iHashCode = this.eventType.hashCode() * 31;
            Integer num = this.unauthId;
            int iHashCode2 = (iHashCode + (num == null ? 0 : num.hashCode())) * 31;
            Integer num2 = this.authAppId;
            int iHashCode3 = (iHashCode2 + (num2 == null ? 0 : num2.hashCode())) * 31;
            String str = this.flowService;
            int iHashCode4 = (iHashCode3 + (str == null ? 0 : str.hashCode())) * 31;
            String str2 = this.flowType;
            int iHashCode5 = (iHashCode4 + (str2 == null ? 0 : str2.hashCode())) * 31;
            EventScreen eventScreen = this.screen;
            int iHashCode6 = (iHashCode5 + (eventScreen == null ? 0 : eventScreen.hashCode())) * 31;
            List<NavigationFieldItem> list = this.fields;
            int iHashCode7 = (iHashCode6 + (list == null ? 0 : list.hashCode())) * 31;
            EventScreen eventScreen2 = this.screenTo;
            return iHashCode7 + (eventScreen2 != null ? eventScreen2.hashCode() : 0);
        }

        @NotNull
        public String toString() {
            return "TypeVkConnectNavigationItem(eventType=" + this.eventType + ", unauthId=" + this.unauthId + ", authAppId=" + this.authAppId + ", flowService=" + this.flowService + ", flowType=" + this.flowType + ", screen=" + this.screen + ", fields=" + this.fields + ", screenTo=" + this.screenTo + ')';
        }

        public /* synthetic */ TypeVkConnectNavigationItem(EventType eventType, Integer num, Integer num2, String str, String str2, EventScreen eventScreen, List list, EventScreen eventScreen2, int i10, DefaultConstructorMarker defaultConstructorMarker) {
            this(eventType, (i10 & 2) != 0 ? null : num, (i10 & 4) != 0 ? null : num2, (i10 & 8) != 0 ? null : str, (i10 & 16) != 0 ? null : str2, (i10 & 32) != 0 ? null : eventScreen, (i10 & 64) != 0 ? null : list, (i10 & 128) != 0 ? null : eventScreen2);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes12.dex */
    @Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b!\b\u0086\b\u0018\u0000 N2\u00020\u0001:\u0003NOPJ\u0010\u0010\u0003\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0003\u0010\u0004J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u0012\u0010\t\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0004\b\t\u0010\nJ\u0012\u0010\f\u001a\u0004\u0018\u00010\u000bHÆ\u0003¢\u0006\u0004\b\f\u0010\rJ\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u000eHÆ\u0003¢\u0006\u0004\b\u000f\u0010\u0010J\u0012\u0010\u0012\u001a\u0004\u0018\u00010\u0011HÆ\u0003¢\u0006\u0004\b\u0012\u0010\u0013J\u0012\u0010\u0015\u001a\u0004\u0018\u00010\u0014HÆ\u0003¢\u0006\u0004\b\u0015\u0010\u0016J\u0012\u0010\u0018\u001a\u0004\u0018\u00010\u0017HÆ\u0003¢\u0006\u0004\b\u0018\u0010\u0019J\u0012\u0010\u001b\u001a\u0004\u0018\u00010\u001aHÆ\u0003¢\u0006\u0004\b\u001b\u0010\u001cJz\u0010&\u001a\u00020\u00002\b\b\u0002\u0010\u001d\u001a\u00020\u00022\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\u00142\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\u00172\n\b\u0002\u0010%\u001a\u0004\u0018\u00010\u001aHÆ\u0001¢\u0006\u0004\b&\u0010'J\u0010\u0010)\u001a\u00020(HÖ\u0001¢\u0006\u0004\b)\u0010*J\u0010\u0010,\u001a\u00020+HÖ\u0001¢\u0006\u0004\b,\u0010-J\u001a\u00101\u001a\u0002002\b\u0010/\u001a\u0004\u0018\u00010.HÖ\u0003¢\u0006\u0004\b1\u00102R\u001a\u0010\u001d\u001a\u00020\u00028\u0006X\u0087\u0004¢\u0006\f\n\u0004\b3\u00104\u001a\u0004\b5\u0010\u0004R\u001c\u0010\u001e\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\f\n\u0004\b6\u00107\u001a\u0004\b8\u0010\u0007R\u001c\u0010\u001f\u001a\u0004\u0018\u00010\b8\u0006X\u0087\u0004¢\u0006\f\n\u0004\b9\u0010:\u001a\u0004\b;\u0010\nR\u001c\u0010 \u001a\u0004\u0018\u00010\u000b8\u0006X\u0087\u0004¢\u0006\f\n\u0004\b<\u0010=\u001a\u0004\b>\u0010\rR\u001c\u0010!\u001a\u0004\u0018\u00010\u000e8\u0006X\u0087\u0004¢\u0006\f\n\u0004\b?\u0010@\u001a\u0004\bA\u0010\u0010R\u001c\u0010\"\u001a\u0004\u0018\u00010\u00118\u0006X\u0087\u0004¢\u0006\f\n\u0004\bB\u0010C\u001a\u0004\bD\u0010\u0013R\u001c\u0010#\u001a\u0004\u0018\u00010\u00148\u0006X\u0087\u0004¢\u0006\f\n\u0004\bE\u0010F\u001a\u0004\bG\u0010\u0016R\u001c\u0010$\u001a\u0004\u0018\u00010\u00178\u0006X\u0087\u0004¢\u0006\f\n\u0004\bH\u0010I\u001a\u0004\bJ\u0010\u0019R\u001c\u0010%\u001a\u0004\u0018\u00010\u001a8\u0006X\u0087\u0004¢\u0006\f\n\u0004\bK\u0010L\u001a\u0004\bM\u0010\u001c¨\u0006Q"}, d2 = {"Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeAction;", "Lcom/vk/stat/sak/scheme/SchemeStatSak$EventProductMain$Payload;", "Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeAction$Type;", "component1", "()Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeAction$Type;", "Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeRegistrationItem;", "component2", "()Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeRegistrationItem;", "Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeVkConnectNavigationItem;", "component3", "()Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeVkConnectNavigationItem;", "Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeSakSessionsEventItem;", "component4", "()Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeSakSessionsEventItem;", "Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeDebugStatsItem;", "component5", "()Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeDebugStatsItem;", "Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeVkPayCheckoutItem;", "component6", "()Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeVkPayCheckoutItem;", "Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeMultiaccountsItem;", "component7", "()Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeMultiaccountsItem;", "Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeErrorShownItem;", "component8", "()Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeErrorShownItem;", "Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeVkidEcosystemNavigationItem;", "component9", "()Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeVkidEcosystemNavigationItem;", "type", "typeRegistrationItem", "typeVkConnectNavigationItem", "typeSakSessionsEventItem", "typeDebugStatsItem", "typeVkPayCheckoutItem", "typeMultiaccountsItem", "typeErrorShownItem", "typeVkidEcosystemNavigationItem", "copy", "(Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeAction$Type;Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeRegistrationItem;Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeVkConnectNavigationItem;Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeSakSessionsEventItem;Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeDebugStatsItem;Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeVkPayCheckoutItem;Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeMultiaccountsItem;Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeErrorShownItem;Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeVkidEcosystemNavigationItem;)Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeAction;", "", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "kastatsbilkvmoca", "Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeAction$Type;", "getType", "kastatsbilkvmocb", "Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeRegistrationItem;", "getTypeRegistrationItem", "kastatsbilkvmocc", "Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeVkConnectNavigationItem;", "getTypeVkConnectNavigationItem", "kastatsbilkvmocd", "Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeSakSessionsEventItem;", "getTypeSakSessionsEventItem", "kastatsbilkvmoce", "Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeDebugStatsItem;", "getTypeDebugStatsItem", "kastatsbilkvmocf", "Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeVkPayCheckoutItem;", "getTypeVkPayCheckoutItem", "kastatsbilkvmocg", "Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeMultiaccountsItem;", "getTypeMultiaccountsItem", "kastatsbilkvmoch", "Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeErrorShownItem;", "getTypeErrorShownItem", "kastatsbilkvmoci", "Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeVkidEcosystemNavigationItem;", "getTypeVkidEcosystemNavigationItem", "Companion", "Type", "Payload", "sak_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final /* data */ class TypeAction implements EventProductMain.Payload {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        @NotNull
        public static final Companion INSTANCE = new Companion(null);

        /* JADX INFO: renamed from: kastatsbilkvmoca, reason: from kotlin metadata */
        @SerializedName("type")
        @NotNull
        private final Type type;

        /* JADX INFO: renamed from: kastatsbilkvmocb, reason: from kotlin metadata */
        @SerializedName("type_registration_item")
        @Nullable
        private final TypeRegistrationItem typeRegistrationItem;

        /* JADX INFO: renamed from: kastatsbilkvmocc, reason: from kotlin metadata */
        @SerializedName("type_vk_connect_navigation_item")
        @Nullable
        private final TypeVkConnectNavigationItem typeVkConnectNavigationItem;

        /* JADX INFO: renamed from: kastatsbilkvmocd, reason: from kotlin metadata */
        @SerializedName("type_sak_sessions_event_item")
        @Nullable
        private final TypeSakSessionsEventItem typeSakSessionsEventItem;

        /* JADX INFO: renamed from: kastatsbilkvmoce, reason: from kotlin metadata */
        @SerializedName("type_debug_stats_item")
        @Nullable
        private final TypeDebugStatsItem typeDebugStatsItem;

        /* JADX INFO: renamed from: kastatsbilkvmocf, reason: from kotlin metadata */
        @SerializedName("type_vk_pay_checkout_item")
        @Nullable
        private final TypeVkPayCheckoutItem typeVkPayCheckoutItem;

        /* JADX INFO: renamed from: kastatsbilkvmocg, reason: from kotlin metadata */
        @SerializedName("type_multiaccounts_item")
        @Nullable
        private final TypeMultiaccountsItem typeMultiaccountsItem;

        /* JADX INFO: renamed from: kastatsbilkvmoch, reason: from kotlin metadata */
        @SerializedName("type_error_shown_item")
        @Nullable
        private final TypeErrorShownItem typeErrorShownItem;

        /* JADX INFO: renamed from: kastatsbilkvmoci, reason: from kotlin metadata */
        @SerializedName("type_vkid_ecosystem_navigation_item")
        @Nullable
        private final TypeVkidEcosystemNavigationItem typeVkidEcosystemNavigationItem;

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeAction$Companion;", "", "<init>", "()V", "create", "Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeAction;", JsonMessageParser.Keys.PAYLOAD_JSON_KEY, "Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeAction$Payload;", "sak_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            @NotNull
            public final TypeAction create(@NotNull Payload payload) {
                Intrinsics.checkNotNullParameter(payload, "payload");
                if (payload instanceof TypeRegistrationItem) {
                    return new TypeAction(Type.TYPE_REGISTRATION_ITEM, (TypeRegistrationItem) payload, null, null, null, null, null, null, null, 508);
                }
                if (payload instanceof TypeVkConnectNavigationItem) {
                    return new TypeAction(Type.TYPE_VK_CONNECT_NAVIGATION_ITEM, null, (TypeVkConnectNavigationItem) payload, null, null, null, null, null, null, VKApiCodes.CODE_VK_PAY_INVALID_AMOUNT);
                }
                if (payload instanceof TypeSakSessionsEventItem) {
                    return new TypeAction(Type.TYPE_SAK_SESSIONS_EVENT_ITEM, null, null, (TypeSakSessionsEventItem) payload, null, null, null, null, null, 502);
                }
                if (payload instanceof TypeDebugStatsItem) {
                    return new TypeAction(Type.TYPE_DEBUG_STATS_ITEM, null, null, null, (TypeDebugStatsItem) payload, null, null, null, null, 494);
                }
                if (payload instanceof TypeVkPayCheckoutItem) {
                    return new TypeAction(Type.TYPE_VK_PAY_CHECKOUT_ITEM, null, null, null, null, (TypeVkPayCheckoutItem) payload, null, null, null, 478);
                }
                if (payload instanceof TypeMultiaccountsItem) {
                    return new TypeAction(Type.TYPE_MULTIACCOUNTS_ITEM, null, null, null, null, null, (TypeMultiaccountsItem) payload, null, null, 446);
                }
                if (payload instanceof TypeErrorShownItem) {
                    return new TypeAction(Type.TYPE_ERROR_SHOWN_ITEM, null, null, null, null, null, null, (TypeErrorShownItem) payload, null, 382);
                }
                if (!(payload instanceof TypeVkidEcosystemNavigationItem)) {
                    throw new IllegalArgumentException("payload must be one of(TypeRegistrationItem, TypeVkConnectNavigationItem, TypeSakSessionsEventItem, TypeDebugStatsItem, TypeVkPayCheckoutItem, TypeMultiaccountsItem, TypeErrorShownItem, TypeVkidEcosystemNavigationItem)");
                }
                return new TypeAction(Type.TYPE_VKID_ECOSYSTEM_NAVIGATION_ITEM, null, null, null, null, null, null, null, (TypeVkidEcosystemNavigationItem) payload, 254);
            }

            private Companion() {
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\bf\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeAction$Payload;", "", "sak_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
        public interface Payload {
        }

        /* JADX WARN: Enum visitor error
        jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v1 com.vk.stat.sak.scheme.SchemeStatSak$TypeAction$Type[], still in use, count: 1, list:
          (r0v1 com.vk.stat.sak.scheme.SchemeStatSak$TypeAction$Type[]) from 0x0056: INVOKE (r0v1 com.vk.stat.sak.scheme.SchemeStatSak$TypeAction$Type[]) STATIC call: kotlin.enums.EnumEntriesKt.enumEntries(java.lang.Enum[]):kotlin.enums.EnumEntries A[MD:<E extends java.lang.Enum<E>>:(E extends java.lang.Enum<E>[]):kotlin.enums.EnumEntries<E extends java.lang.Enum<E>> (m), WRAPPED] (LINE:87)
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
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\t\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001j\u0002\b\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, d2 = {"Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeAction$Type;", "", "TYPE_REGISTRATION_ITEM", "TYPE_VK_CONNECT_NAVIGATION_ITEM", "TYPE_SAK_SESSIONS_EVENT_ITEM", "TYPE_DEBUG_STATS_ITEM", "TYPE_VK_PAY_CHECKOUT_ITEM", "TYPE_MULTIACCOUNTS_ITEM", "TYPE_ERROR_SHOWN_ITEM", "TYPE_VKID_ECOSYSTEM_NAVIGATION_ITEM", "sak_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
        public static final class Type {
            TYPE_REGISTRATION_ITEM,
            TYPE_VK_CONNECT_NAVIGATION_ITEM,
            TYPE_SAK_SESSIONS_EVENT_ITEM,
            TYPE_DEBUG_STATS_ITEM,
            TYPE_VK_PAY_CHECKOUT_ITEM,
            TYPE_MULTIACCOUNTS_ITEM,
            TYPE_ERROR_SHOWN_ITEM,
            TYPE_VKID_ECOSYSTEM_NAVIGATION_ITEM;

            private static final /* synthetic */ EnumEntries kastatsbilkvmocb;

            static {
                kastatsbilkvmocb = EnumEntriesKt.enumEntries(typeArr);
            }

            private Type() {
                super(str, i);
            }

            @NotNull
            public static EnumEntries<Type> getEntries() {
                return kastatsbilkvmocb;
            }

            public static Type valueOf(String str) {
                return (Type) Enum.valueOf(Type.class, str);
            }

            public static Type[] values() {
                return (Type[]) kastatsbilkvmoca.clone();
            }
        }

        private TypeAction(Type type, TypeRegistrationItem typeRegistrationItem, TypeVkConnectNavigationItem typeVkConnectNavigationItem, TypeSakSessionsEventItem typeSakSessionsEventItem, TypeDebugStatsItem typeDebugStatsItem, TypeVkPayCheckoutItem typeVkPayCheckoutItem, TypeMultiaccountsItem typeMultiaccountsItem, TypeErrorShownItem typeErrorShownItem, TypeVkidEcosystemNavigationItem typeVkidEcosystemNavigationItem) {
            this.type = type;
            this.typeRegistrationItem = typeRegistrationItem;
            this.typeVkConnectNavigationItem = typeVkConnectNavigationItem;
            this.typeSakSessionsEventItem = typeSakSessionsEventItem;
            this.typeDebugStatsItem = typeDebugStatsItem;
            this.typeVkPayCheckoutItem = typeVkPayCheckoutItem;
            this.typeMultiaccountsItem = typeMultiaccountsItem;
            this.typeErrorShownItem = typeErrorShownItem;
            this.typeVkidEcosystemNavigationItem = typeVkidEcosystemNavigationItem;
        }

        public static /* synthetic */ TypeAction copy$default(TypeAction typeAction, Type type, TypeRegistrationItem typeRegistrationItem, TypeVkConnectNavigationItem typeVkConnectNavigationItem, TypeSakSessionsEventItem typeSakSessionsEventItem, TypeDebugStatsItem typeDebugStatsItem, TypeVkPayCheckoutItem typeVkPayCheckoutItem, TypeMultiaccountsItem typeMultiaccountsItem, TypeErrorShownItem typeErrorShownItem, TypeVkidEcosystemNavigationItem typeVkidEcosystemNavigationItem, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                type = typeAction.type;
            }
            if ((i10 & 2) != 0) {
                typeRegistrationItem = typeAction.typeRegistrationItem;
            }
            if ((i10 & 4) != 0) {
                typeVkConnectNavigationItem = typeAction.typeVkConnectNavigationItem;
            }
            if ((i10 & 8) != 0) {
                typeSakSessionsEventItem = typeAction.typeSakSessionsEventItem;
            }
            if ((i10 & 16) != 0) {
                typeDebugStatsItem = typeAction.typeDebugStatsItem;
            }
            if ((i10 & 32) != 0) {
                typeVkPayCheckoutItem = typeAction.typeVkPayCheckoutItem;
            }
            if ((i10 & 64) != 0) {
                typeMultiaccountsItem = typeAction.typeMultiaccountsItem;
            }
            if ((i10 & 128) != 0) {
                typeErrorShownItem = typeAction.typeErrorShownItem;
            }
            if ((i10 & 256) != 0) {
                typeVkidEcosystemNavigationItem = typeAction.typeVkidEcosystemNavigationItem;
            }
            TypeErrorShownItem typeErrorShownItem2 = typeErrorShownItem;
            TypeVkidEcosystemNavigationItem typeVkidEcosystemNavigationItem2 = typeVkidEcosystemNavigationItem;
            TypeVkPayCheckoutItem typeVkPayCheckoutItem2 = typeVkPayCheckoutItem;
            TypeMultiaccountsItem typeMultiaccountsItem2 = typeMultiaccountsItem;
            TypeDebugStatsItem typeDebugStatsItem2 = typeDebugStatsItem;
            TypeVkConnectNavigationItem typeVkConnectNavigationItem2 = typeVkConnectNavigationItem;
            return typeAction.copy(type, typeRegistrationItem, typeVkConnectNavigationItem2, typeSakSessionsEventItem, typeDebugStatsItem2, typeVkPayCheckoutItem2, typeMultiaccountsItem2, typeErrorShownItem2, typeVkidEcosystemNavigationItem2);
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Type getType() {
            return this.type;
        }

        @Nullable
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final TypeRegistrationItem getTypeRegistrationItem() {
            return this.typeRegistrationItem;
        }

        @Nullable
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final TypeVkConnectNavigationItem getTypeVkConnectNavigationItem() {
            return this.typeVkConnectNavigationItem;
        }

        @Nullable
        /* JADX INFO: renamed from: component4, reason: from getter */
        public final TypeSakSessionsEventItem getTypeSakSessionsEventItem() {
            return this.typeSakSessionsEventItem;
        }

        @Nullable
        /* JADX INFO: renamed from: component5, reason: from getter */
        public final TypeDebugStatsItem getTypeDebugStatsItem() {
            return this.typeDebugStatsItem;
        }

        @Nullable
        /* JADX INFO: renamed from: component6, reason: from getter */
        public final TypeVkPayCheckoutItem getTypeVkPayCheckoutItem() {
            return this.typeVkPayCheckoutItem;
        }

        @Nullable
        /* JADX INFO: renamed from: component7, reason: from getter */
        public final TypeMultiaccountsItem getTypeMultiaccountsItem() {
            return this.typeMultiaccountsItem;
        }

        @Nullable
        /* JADX INFO: renamed from: component8, reason: from getter */
        public final TypeErrorShownItem getTypeErrorShownItem() {
            return this.typeErrorShownItem;
        }

        @Nullable
        /* JADX INFO: renamed from: component9, reason: from getter */
        public final TypeVkidEcosystemNavigationItem getTypeVkidEcosystemNavigationItem() {
            return this.typeVkidEcosystemNavigationItem;
        }

        @NotNull
        public final TypeAction copy(@NotNull Type type, @Nullable TypeRegistrationItem typeRegistrationItem, @Nullable TypeVkConnectNavigationItem typeVkConnectNavigationItem, @Nullable TypeSakSessionsEventItem typeSakSessionsEventItem, @Nullable TypeDebugStatsItem typeDebugStatsItem, @Nullable TypeVkPayCheckoutItem typeVkPayCheckoutItem, @Nullable TypeMultiaccountsItem typeMultiaccountsItem, @Nullable TypeErrorShownItem typeErrorShownItem, @Nullable TypeVkidEcosystemNavigationItem typeVkidEcosystemNavigationItem) {
            Intrinsics.checkNotNullParameter(type, "type");
            return new TypeAction(type, typeRegistrationItem, typeVkConnectNavigationItem, typeSakSessionsEventItem, typeDebugStatsItem, typeVkPayCheckoutItem, typeMultiaccountsItem, typeErrorShownItem, typeVkidEcosystemNavigationItem);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof TypeAction)) {
                return false;
            }
            TypeAction typeAction = (TypeAction) other;
            return this.type == typeAction.type && Intrinsics.areEqual(this.typeRegistrationItem, typeAction.typeRegistrationItem) && Intrinsics.areEqual(this.typeVkConnectNavigationItem, typeAction.typeVkConnectNavigationItem) && Intrinsics.areEqual(this.typeSakSessionsEventItem, typeAction.typeSakSessionsEventItem) && Intrinsics.areEqual(this.typeDebugStatsItem, typeAction.typeDebugStatsItem) && Intrinsics.areEqual(this.typeVkPayCheckoutItem, typeAction.typeVkPayCheckoutItem) && Intrinsics.areEqual(this.typeMultiaccountsItem, typeAction.typeMultiaccountsItem) && Intrinsics.areEqual(this.typeErrorShownItem, typeAction.typeErrorShownItem) && Intrinsics.areEqual(this.typeVkidEcosystemNavigationItem, typeAction.typeVkidEcosystemNavigationItem);
        }

        @NotNull
        public final Type getType() {
            return this.type;
        }

        @Nullable
        public final TypeDebugStatsItem getTypeDebugStatsItem() {
            return this.typeDebugStatsItem;
        }

        @Nullable
        public final TypeErrorShownItem getTypeErrorShownItem() {
            return this.typeErrorShownItem;
        }

        @Nullable
        public final TypeMultiaccountsItem getTypeMultiaccountsItem() {
            return this.typeMultiaccountsItem;
        }

        @Nullable
        public final TypeRegistrationItem getTypeRegistrationItem() {
            return this.typeRegistrationItem;
        }

        @Nullable
        public final TypeSakSessionsEventItem getTypeSakSessionsEventItem() {
            return this.typeSakSessionsEventItem;
        }

        @Nullable
        public final TypeVkConnectNavigationItem getTypeVkConnectNavigationItem() {
            return this.typeVkConnectNavigationItem;
        }

        @Nullable
        public final TypeVkPayCheckoutItem getTypeVkPayCheckoutItem() {
            return this.typeVkPayCheckoutItem;
        }

        @Nullable
        public final TypeVkidEcosystemNavigationItem getTypeVkidEcosystemNavigationItem() {
            return this.typeVkidEcosystemNavigationItem;
        }

        public int hashCode() {
            int iHashCode = this.type.hashCode() * 31;
            TypeRegistrationItem typeRegistrationItem = this.typeRegistrationItem;
            int iHashCode2 = (iHashCode + (typeRegistrationItem == null ? 0 : typeRegistrationItem.hashCode())) * 31;
            TypeVkConnectNavigationItem typeVkConnectNavigationItem = this.typeVkConnectNavigationItem;
            int iHashCode3 = (iHashCode2 + (typeVkConnectNavigationItem == null ? 0 : typeVkConnectNavigationItem.hashCode())) * 31;
            TypeSakSessionsEventItem typeSakSessionsEventItem = this.typeSakSessionsEventItem;
            int iHashCode4 = (iHashCode3 + (typeSakSessionsEventItem == null ? 0 : typeSakSessionsEventItem.hashCode())) * 31;
            TypeDebugStatsItem typeDebugStatsItem = this.typeDebugStatsItem;
            int iHashCode5 = (iHashCode4 + (typeDebugStatsItem == null ? 0 : typeDebugStatsItem.hashCode())) * 31;
            TypeVkPayCheckoutItem typeVkPayCheckoutItem = this.typeVkPayCheckoutItem;
            int iHashCode6 = (iHashCode5 + (typeVkPayCheckoutItem == null ? 0 : typeVkPayCheckoutItem.hashCode())) * 31;
            TypeMultiaccountsItem typeMultiaccountsItem = this.typeMultiaccountsItem;
            int iHashCode7 = (iHashCode6 + (typeMultiaccountsItem == null ? 0 : typeMultiaccountsItem.hashCode())) * 31;
            TypeErrorShownItem typeErrorShownItem = this.typeErrorShownItem;
            int iHashCode8 = (iHashCode7 + (typeErrorShownItem == null ? 0 : typeErrorShownItem.hashCode())) * 31;
            TypeVkidEcosystemNavigationItem typeVkidEcosystemNavigationItem = this.typeVkidEcosystemNavigationItem;
            return iHashCode8 + (typeVkidEcosystemNavigationItem != null ? typeVkidEcosystemNavigationItem.hashCode() : 0);
        }

        @NotNull
        public String toString() {
            return "TypeAction(type=" + this.type + ", typeRegistrationItem=" + this.typeRegistrationItem + ", typeVkConnectNavigationItem=" + this.typeVkConnectNavigationItem + ", typeSakSessionsEventItem=" + this.typeSakSessionsEventItem + ", typeDebugStatsItem=" + this.typeDebugStatsItem + ", typeVkPayCheckoutItem=" + this.typeVkPayCheckoutItem + ", typeMultiaccountsItem=" + this.typeMultiaccountsItem + ", typeErrorShownItem=" + this.typeErrorShownItem + ", typeVkidEcosystemNavigationItem=" + this.typeVkidEcosystemNavigationItem + ')';
        }

        /* synthetic */ TypeAction(Type type, TypeRegistrationItem typeRegistrationItem, TypeVkConnectNavigationItem typeVkConnectNavigationItem, TypeSakSessionsEventItem typeSakSessionsEventItem, TypeDebugStatsItem typeDebugStatsItem, TypeVkPayCheckoutItem typeVkPayCheckoutItem, TypeMultiaccountsItem typeMultiaccountsItem, TypeErrorShownItem typeErrorShownItem, TypeVkidEcosystemNavigationItem typeVkidEcosystemNavigationItem, int i10) {
            this(type, (i10 & 2) != 0 ? null : typeRegistrationItem, (i10 & 4) != 0 ? null : typeVkConnectNavigationItem, (i10 & 8) != 0 ? null : typeSakSessionsEventItem, (i10 & 16) != 0 ? null : typeDebugStatsItem, (i10 & 32) != 0 ? null : typeVkPayCheckoutItem, (i10 & 64) != 0 ? null : typeMultiaccountsItem, (i10 & 128) != 0 ? null : typeErrorShownItem, (i10 & 256) != 0 ? null : typeVkidEcosystemNavigationItem);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes12.dex */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0015\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u001a\b\u0086\b\u0018\u00002\u00020\u0001Bo\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\u000f\u0010\u0010J\u0010\u0010\u0011\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0011\u0010\u0012J\u0010\u0010\u0013\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b\u0013\u0010\u0014J\u0010\u0010\u0015\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0015\u0010\u0012J\u0010\u0010\u0016\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0016\u0010\u0012J\u0012\u0010\u0017\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0004\b\u0017\u0010\u0014J\u0012\u0010\u0018\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u0018\u0010\u0012J\u0012\u0010\u0019\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u0019\u0010\u0012J\u0012\u0010\u001a\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u001a\u0010\u0012J\u0012\u0010\u001b\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u001b\u0010\u0012J\u0012\u0010\u001c\u001a\u0004\u0018\u00010\rHÆ\u0003¢\u0006\u0004\b\u001c\u0010\u001dJ\u0080\u0001\u0010\u001e\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00022\b\b\u0002\u0010\u0007\u001a\u00020\u00022\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\rHÆ\u0001¢\u0006\u0004\b\u001e\u0010\u001fJ\u0010\u0010 \u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b \u0010\u0012J\u0010\u0010!\u001a\u00020\rHÖ\u0001¢\u0006\u0004\b!\u0010\"J\u001a\u0010&\u001a\u00020%2\b\u0010$\u001a\u0004\u0018\u00010#HÖ\u0003¢\u0006\u0004\b&\u0010'R\u001a\u0010\u0003\u001a\u00020\u00028\u0006X\u0087\u0004¢\u0006\f\n\u0004\b(\u0010)\u001a\u0004\b*\u0010\u0012R\u001a\u0010\u0005\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\f\n\u0004\b+\u0010,\u001a\u0004\b-\u0010\u0014R\u001a\u0010\u0006\u001a\u00020\u00028\u0006X\u0087\u0004¢\u0006\f\n\u0004\b.\u0010)\u001a\u0004\b/\u0010\u0012R\u001a\u0010\u0007\u001a\u00020\u00028\u0006X\u0087\u0004¢\u0006\f\n\u0004\b0\u0010)\u001a\u0004\b1\u0010\u0012R\u001c\u0010\b\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004¢\u0006\f\n\u0004\b2\u0010,\u001a\u0004\b3\u0010\u0014R\u001c\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006X\u0087\u0004¢\u0006\f\n\u0004\b4\u0010)\u001a\u0004\b5\u0010\u0012R\u001c\u0010\n\u001a\u0004\u0018\u00010\u00028\u0006X\u0087\u0004¢\u0006\f\n\u0004\b6\u0010)\u001a\u0004\b7\u0010\u0012R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\u00028\u0006X\u0087\u0004¢\u0006\f\n\u0004\b8\u0010)\u001a\u0004\b9\u0010\u0012R\u001c\u0010\f\u001a\u0004\u0018\u00010\u00028\u0006X\u0087\u0004¢\u0006\f\n\u0004\b:\u0010)\u001a\u0004\b;\u0010\u0012R\u001c\u0010\u000e\u001a\u0004\u0018\u00010\r8\u0006X\u0087\u0004¢\u0006\f\n\u0004\b<\u0010=\u001a\u0004\b>\u0010\u001d¨\u0006?"}, d2 = {"Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeErrorShownItem;", "Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeAction$Payload;", "", "backendSection", "Lcom/vk/stat/sak/scheme/SchemeStatSak$ErrorView;", "actualView", "error", "backendMethod", Promotion.ACTION_VIEW, "errorDescription", "actualErrorDescription", "errorCode", "errorSubcode", "", "unauthId", "<init>", "(Ljava/lang/String;Lcom/vk/stat/sak/scheme/SchemeStatSak$ErrorView;Ljava/lang/String;Ljava/lang/String;Lcom/vk/stat/sak/scheme/SchemeStatSak$ErrorView;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;)V", "component1", "()Ljava/lang/String;", "component2", "()Lcom/vk/stat/sak/scheme/SchemeStatSak$ErrorView;", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "()Ljava/lang/Integer;", "copy", "(Ljava/lang/String;Lcom/vk/stat/sak/scheme/SchemeStatSak$ErrorView;Ljava/lang/String;Ljava/lang/String;Lcom/vk/stat/sak/scheme/SchemeStatSak$ErrorView;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;)Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeErrorShownItem;", "toString", "hashCode", "()I", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "kastatsbilkvmoca", "Ljava/lang/String;", "getBackendSection", "kastatsbilkvmocb", "Lcom/vk/stat/sak/scheme/SchemeStatSak$ErrorView;", "getActualView", "kastatsbilkvmocc", "getError", "kastatsbilkvmocd", "getBackendMethod", "kastatsbilkvmoce", "getView", "kastatsbilkvmocf", "getErrorDescription", "kastatsbilkvmocg", "getActualErrorDescription", "kastatsbilkvmoch", "getErrorCode", "kastatsbilkvmoci", "getErrorSubcode", "kastatsbilkvmocj", "Ljava/lang/Integer;", "getUnauthId", "sak_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final /* data */ class TypeErrorShownItem implements TypeAction.Payload {

        /* JADX INFO: renamed from: kastatsbilkvmoca, reason: from kotlin metadata */
        @SerializedName("backend_section")
        @NotNull
        private final String backendSection;

        /* JADX INFO: renamed from: kastatsbilkvmocb, reason: from kotlin metadata */
        @SerializedName("actual_view")
        @NotNull
        private final ErrorView actualView;

        /* JADX INFO: renamed from: kastatsbilkvmocc, reason: from kotlin metadata */
        @SerializedName("error")
        @NotNull
        private final String error;

        /* JADX INFO: renamed from: kastatsbilkvmocd, reason: from kotlin metadata */
        @SerializedName("backend_method")
        @NotNull
        private final String backendMethod;

        /* JADX INFO: renamed from: kastatsbilkvmoce, reason: from kotlin metadata */
        @SerializedName(Promotion.ACTION_VIEW)
        @Nullable
        private final ErrorView view;

        /* JADX INFO: renamed from: kastatsbilkvmocf, reason: from kotlin metadata */
        @SerializedName("error_description")
        @Nullable
        private final String errorDescription;

        /* JADX INFO: renamed from: kastatsbilkvmocg, reason: from kotlin metadata */
        @SerializedName("actual_error_description")
        @Nullable
        private final String actualErrorDescription;

        /* JADX INFO: renamed from: kastatsbilkvmoch, reason: from kotlin metadata */
        @SerializedName("error_code")
        @Nullable
        private final String errorCode;

        /* JADX INFO: renamed from: kastatsbilkvmoci, reason: from kotlin metadata */
        @SerializedName("error_subcode")
        @Nullable
        private final String errorSubcode;

        /* JADX INFO: renamed from: kastatsbilkvmocj, reason: from kotlin metadata */
        @SerializedName("unauth_id")
        @Nullable
        private final Integer unauthId;

        public TypeErrorShownItem(@NotNull String backendSection, @NotNull ErrorView actualView, @NotNull String error, @NotNull String backendMethod, @Nullable ErrorView errorView, @Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable Integer num) {
            Intrinsics.checkNotNullParameter(backendSection, "backendSection");
            Intrinsics.checkNotNullParameter(actualView, "actualView");
            Intrinsics.checkNotNullParameter(error, "error");
            Intrinsics.checkNotNullParameter(backendMethod, "backendMethod");
            this.backendSection = backendSection;
            this.actualView = actualView;
            this.error = error;
            this.backendMethod = backendMethod;
            this.view = errorView;
            this.errorDescription = str;
            this.actualErrorDescription = str2;
            this.errorCode = str3;
            this.errorSubcode = str4;
            this.unauthId = num;
        }

        public static /* synthetic */ TypeErrorShownItem copy$default(TypeErrorShownItem typeErrorShownItem, String str, ErrorView errorView, String str2, String str3, ErrorView errorView2, String str4, String str5, String str6, String str7, Integer num, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = typeErrorShownItem.backendSection;
            }
            if ((i10 & 2) != 0) {
                errorView = typeErrorShownItem.actualView;
            }
            if ((i10 & 4) != 0) {
                str2 = typeErrorShownItem.error;
            }
            if ((i10 & 8) != 0) {
                str3 = typeErrorShownItem.backendMethod;
            }
            if ((i10 & 16) != 0) {
                errorView2 = typeErrorShownItem.view;
            }
            if ((i10 & 32) != 0) {
                str4 = typeErrorShownItem.errorDescription;
            }
            if ((i10 & 64) != 0) {
                str5 = typeErrorShownItem.actualErrorDescription;
            }
            if ((i10 & 128) != 0) {
                str6 = typeErrorShownItem.errorCode;
            }
            if ((i10 & 256) != 0) {
                str7 = typeErrorShownItem.errorSubcode;
            }
            if ((i10 & 512) != 0) {
                num = typeErrorShownItem.unauthId;
            }
            String str8 = str7;
            Integer num2 = num;
            String str9 = str5;
            String str10 = str6;
            ErrorView errorView3 = errorView2;
            String str11 = str4;
            return typeErrorShownItem.copy(str, errorView, str2, str3, errorView3, str11, str9, str10, str8, num2);
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getBackendSection() {
            return this.backendSection;
        }

        @Nullable
        /* JADX INFO: renamed from: component10, reason: from getter */
        public final Integer getUnauthId() {
            return this.unauthId;
        }

        @NotNull
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final ErrorView getActualView() {
            return this.actualView;
        }

        @NotNull
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getError() {
            return this.error;
        }

        @NotNull
        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getBackendMethod() {
            return this.backendMethod;
        }

        @Nullable
        /* JADX INFO: renamed from: component5, reason: from getter */
        public final ErrorView getView() {
            return this.view;
        }

        @Nullable
        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getErrorDescription() {
            return this.errorDescription;
        }

        @Nullable
        /* JADX INFO: renamed from: component7, reason: from getter */
        public final String getActualErrorDescription() {
            return this.actualErrorDescription;
        }

        @Nullable
        /* JADX INFO: renamed from: component8, reason: from getter */
        public final String getErrorCode() {
            return this.errorCode;
        }

        @Nullable
        /* JADX INFO: renamed from: component9, reason: from getter */
        public final String getErrorSubcode() {
            return this.errorSubcode;
        }

        @NotNull
        public final TypeErrorShownItem copy(@NotNull String backendSection, @NotNull ErrorView actualView, @NotNull String error, @NotNull String backendMethod, @Nullable ErrorView view, @Nullable String errorDescription, @Nullable String actualErrorDescription, @Nullable String errorCode, @Nullable String errorSubcode, @Nullable Integer unauthId) {
            Intrinsics.checkNotNullParameter(backendSection, "backendSection");
            Intrinsics.checkNotNullParameter(actualView, "actualView");
            Intrinsics.checkNotNullParameter(error, "error");
            Intrinsics.checkNotNullParameter(backendMethod, "backendMethod");
            return new TypeErrorShownItem(backendSection, actualView, error, backendMethod, view, errorDescription, actualErrorDescription, errorCode, errorSubcode, unauthId);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof TypeErrorShownItem)) {
                return false;
            }
            TypeErrorShownItem typeErrorShownItem = (TypeErrorShownItem) other;
            return Intrinsics.areEqual(this.backendSection, typeErrorShownItem.backendSection) && this.actualView == typeErrorShownItem.actualView && Intrinsics.areEqual(this.error, typeErrorShownItem.error) && Intrinsics.areEqual(this.backendMethod, typeErrorShownItem.backendMethod) && this.view == typeErrorShownItem.view && Intrinsics.areEqual(this.errorDescription, typeErrorShownItem.errorDescription) && Intrinsics.areEqual(this.actualErrorDescription, typeErrorShownItem.actualErrorDescription) && Intrinsics.areEqual(this.errorCode, typeErrorShownItem.errorCode) && Intrinsics.areEqual(this.errorSubcode, typeErrorShownItem.errorSubcode) && Intrinsics.areEqual(this.unauthId, typeErrorShownItem.unauthId);
        }

        @Nullable
        public final String getActualErrorDescription() {
            return this.actualErrorDescription;
        }

        @NotNull
        public final ErrorView getActualView() {
            return this.actualView;
        }

        @NotNull
        public final String getBackendMethod() {
            return this.backendMethod;
        }

        @NotNull
        public final String getBackendSection() {
            return this.backendSection;
        }

        @NotNull
        public final String getError() {
            return this.error;
        }

        @Nullable
        public final String getErrorCode() {
            return this.errorCode;
        }

        @Nullable
        public final String getErrorDescription() {
            return this.errorDescription;
        }

        @Nullable
        public final String getErrorSubcode() {
            return this.errorSubcode;
        }

        @Nullable
        public final Integer getUnauthId() {
            return this.unauthId;
        }

        @Nullable
        public final ErrorView getView() {
            return this.view;
        }

        public int hashCode() {
            int iHashCode = (this.backendMethod.hashCode() + ((this.error.hashCode() + ((this.actualView.hashCode() + (this.backendSection.hashCode() * 31)) * 31)) * 31)) * 31;
            ErrorView errorView = this.view;
            int iHashCode2 = (iHashCode + (errorView == null ? 0 : errorView.hashCode())) * 31;
            String str = this.errorDescription;
            int iHashCode3 = (iHashCode2 + (str == null ? 0 : str.hashCode())) * 31;
            String str2 = this.actualErrorDescription;
            int iHashCode4 = (iHashCode3 + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.errorCode;
            int iHashCode5 = (iHashCode4 + (str3 == null ? 0 : str3.hashCode())) * 31;
            String str4 = this.errorSubcode;
            int iHashCode6 = (iHashCode5 + (str4 == null ? 0 : str4.hashCode())) * 31;
            Integer num = this.unauthId;
            return iHashCode6 + (num != null ? num.hashCode() : 0);
        }

        @NotNull
        public String toString() {
            return "TypeErrorShownItem(backendSection=" + this.backendSection + ", actualView=" + this.actualView + ", error=" + this.error + ", backendMethod=" + this.backendMethod + ", view=" + this.view + ", errorDescription=" + this.errorDescription + ", actualErrorDescription=" + this.actualErrorDescription + ", errorCode=" + this.errorCode + ", errorSubcode=" + this.errorSubcode + ", unauthId=" + this.unauthId + ')';
        }

        public /* synthetic */ TypeErrorShownItem(String str, ErrorView errorView, String str2, String str3, ErrorView errorView2, String str4, String str5, String str6, String str7, Integer num, int i10, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, errorView, str2, str3, (i10 & 16) != 0 ? null : errorView2, (i10 & 32) != 0 ? null : str4, (i10 & 64) != 0 ? null : str5, (i10 & 128) != 0 ? null : str6, (i10 & 256) != 0 ? null : str7, (i10 & 512) != 0 ? null : num);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes12.dex */
    @Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u001e\b\u0086\b\u0018\u00002\u00020\u0001:\u0002GHBu\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0010\t\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0010¢\u0006\u0004\b\u0012\u0010\u0013J\u0010\u0010\u0014\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0014\u0010\u0015J\u0010\u0010\u0016\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b\u0016\u0010\u0017J\u0016\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006HÆ\u0003¢\u0006\u0004\b\u0018\u0010\u0019J\u0010\u0010\u001a\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b\u001a\u0010\u0017J\u0012\u0010\u001b\u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0004\b\u001b\u0010\u001cJ\u0012\u0010\u001d\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0004\b\u001d\u0010\u0017J\u0012\u0010\u001e\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0004\b\u001e\u0010\u0017J\u0012\u0010\u001f\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0004\b\u001f\u0010\u0017J\u0012\u0010 \u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0004\b \u0010\u0017J\u0012\u0010!\u001a\u0004\u0018\u00010\u0010HÆ\u0003¢\u0006\u0004\b!\u0010\"J\u0086\u0001\u0010#\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\b\b\u0002\u0010\t\u001a\u00020\u00042\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0010HÆ\u0001¢\u0006\u0004\b#\u0010$J\u0010\u0010%\u001a\u00020\u0004HÖ\u0001¢\u0006\u0004\b%\u0010\u0017J\u0010\u0010'\u001a\u00020&HÖ\u0001¢\u0006\u0004\b'\u0010(J\u001a\u0010,\u001a\u00020+2\b\u0010*\u001a\u0004\u0018\u00010)HÖ\u0003¢\u0006\u0004\b,\u0010-R\u001a\u0010\u0003\u001a\u00020\u00028\u0006X\u0087\u0004¢\u0006\f\n\u0004\b.\u0010/\u001a\u0004\b0\u0010\u0015R\u001a\u0010\u0005\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\f\n\u0004\b1\u00102\u001a\u0004\b3\u0010\u0017R \u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068\u0006X\u0087\u0004¢\u0006\f\n\u0004\b4\u00105\u001a\u0004\b6\u0010\u0019R\u001a\u0010\t\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\f\n\u0004\b7\u00102\u001a\u0004\b8\u0010\u0017R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\n8\u0006X\u0087\u0004¢\u0006\f\n\u0004\b9\u0010:\u001a\u0004\b;\u0010\u001cR\u001c\u0010\f\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004¢\u0006\f\n\u0004\b<\u00102\u001a\u0004\b=\u0010\u0017R\u001c\u0010\r\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004¢\u0006\f\n\u0004\b>\u00102\u001a\u0004\b?\u0010\u0017R\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004¢\u0006\f\n\u0004\b@\u00102\u001a\u0004\bA\u0010\u0017R\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004¢\u0006\f\n\u0004\bB\u00102\u001a\u0004\bC\u0010\u0017R\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u00108\u0006X\u0087\u0004¢\u0006\f\n\u0004\bD\u0010E\u001a\u0004\bF\u0010\"¨\u0006I"}, d2 = {"Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeVkidEcosystemNavigationItem;", "Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeAction$Payload;", "Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeVkidEcosystemNavigationItem$Event;", "event", "", "screen", "", "Lcom/vk/stat/sak/scheme/SchemeStatSak$EcosystemNavigationOptionItem;", "options", "metadata", "Lcom/vk/stat/sak/scheme/SchemeStatSak$EcosystemNavigationItem;", DivActionSpec.Scroll.PARAM_ITEM_INDEX, "multiaccId", "appLanguage", "osLanguage", "osCountry", "Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeVkidEcosystemNavigationItem$Env;", "env", "<init>", "(Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeVkidEcosystemNavigationItem$Event;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Lcom/vk/stat/sak/scheme/SchemeStatSak$EcosystemNavigationItem;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeVkidEcosystemNavigationItem$Env;)V", "component1", "()Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeVkidEcosystemNavigationItem$Event;", "component2", "()Ljava/lang/String;", "component3", "()Ljava/util/List;", "component4", "component5", "()Lcom/vk/stat/sak/scheme/SchemeStatSak$EcosystemNavigationItem;", "component6", "component7", "component8", "component9", "component10", "()Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeVkidEcosystemNavigationItem$Env;", "copy", "(Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeVkidEcosystemNavigationItem$Event;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Lcom/vk/stat/sak/scheme/SchemeStatSak$EcosystemNavigationItem;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeVkidEcosystemNavigationItem$Env;)Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeVkidEcosystemNavigationItem;", "toString", "", "hashCode", "()I", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "kastatsbilkvmoca", "Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeVkidEcosystemNavigationItem$Event;", "getEvent", "kastatsbilkvmocb", "Ljava/lang/String;", "getScreen", "kastatsbilkvmocc", "Ljava/util/List;", "getOptions", "kastatsbilkvmocd", "getMetadata", "kastatsbilkvmoce", "Lcom/vk/stat/sak/scheme/SchemeStatSak$EcosystemNavigationItem;", "getItem", "kastatsbilkvmocf", "getMultiaccId", "kastatsbilkvmocg", "getAppLanguage", "kastatsbilkvmoch", "getOsLanguage", "kastatsbilkvmoci", "getOsCountry", "kastatsbilkvmocj", "Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeVkidEcosystemNavigationItem$Env;", "getEnv", "Event", "Env", "sak_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final /* data */ class TypeVkidEcosystemNavigationItem implements TypeAction.Payload {

        /* JADX INFO: renamed from: kastatsbilkvmoca, reason: from kotlin metadata */
        @SerializedName("event")
        @NotNull
        private final Event event;

        /* JADX INFO: renamed from: kastatsbilkvmocb, reason: from kotlin metadata */
        @SerializedName("screen")
        @NotNull
        private final String screen;

        /* JADX INFO: renamed from: kastatsbilkvmocc, reason: from kotlin metadata */
        @SerializedName("options")
        @NotNull
        private final List<EcosystemNavigationOptionItem> options;

        /* JADX INFO: renamed from: kastatsbilkvmocd, reason: from kotlin metadata */
        @SerializedName("metadata")
        @NotNull
        private final String metadata;

        /* JADX INFO: renamed from: kastatsbilkvmoce, reason: from kotlin metadata */
        @SerializedName(DivActionSpec.Scroll.PARAM_ITEM_INDEX)
        @Nullable
        private final EcosystemNavigationItem item;

        /* JADX INFO: renamed from: kastatsbilkvmocf, reason: from kotlin metadata */
        @SerializedName("multiacc_id")
        @Nullable
        private final String multiaccId;

        /* JADX INFO: renamed from: kastatsbilkvmocg, reason: from kotlin metadata */
        @SerializedName("app_language")
        @Nullable
        private final String appLanguage;

        /* JADX INFO: renamed from: kastatsbilkvmoch, reason: from kotlin metadata */
        @SerializedName("os_language")
        @Nullable
        private final String osLanguage;

        /* JADX INFO: renamed from: kastatsbilkvmoci, reason: from kotlin metadata */
        @SerializedName("os_country")
        @Nullable
        private final String osCountry;

        /* JADX INFO: renamed from: kastatsbilkvmocj, reason: from kotlin metadata */
        @SerializedName("env")
        @Nullable
        private final Env env;

        /* JADX WARN: Enum visitor error
        jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v1 com.vk.stat.sak.scheme.SchemeStatSak$TypeVkidEcosystemNavigationItem$Env[], still in use, count: 1, list:
          (r0v1 com.vk.stat.sak.scheme.SchemeStatSak$TypeVkidEcosystemNavigationItem$Env[]) from 0x0024: INVOKE (r0v1 com.vk.stat.sak.scheme.SchemeStatSak$TypeVkidEcosystemNavigationItem$Env[]) STATIC call: kotlin.enums.EnumEntriesKt.enumEntries(java.lang.Enum[]):kotlin.enums.EnumEntries A[MD:<E extends java.lang.Enum<E>>:(E extends java.lang.Enum<E>[]):kotlin.enums.EnumEntries<E extends java.lang.Enum<E>> (m), WRAPPED] (LINE:37)
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
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001j\u0002\b\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, d2 = {"Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeVkidEcosystemNavigationItem$Env;", "", "DEVELOPMENT", "PRODUCTION", "TESTING", "sak_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
        public static final class Env {
            DEVELOPMENT,
            PRODUCTION,
            TESTING;

            private static final /* synthetic */ EnumEntries kastatsbilkvmocb;

            static {
                kastatsbilkvmocb = EnumEntriesKt.enumEntries(envArr);
            }

            private Env() {
                super(str, i);
            }

            @NotNull
            public static EnumEntries<Env> getEntries() {
                return kastatsbilkvmocb;
            }

            public static Env valueOf(String str) {
                return (Env) Enum.valueOf(Env.class, str);
            }

            public static Env[] values() {
                return (Env[]) kastatsbilkvmoca.clone();
            }
        }

        /* JADX WARN: Enum visitor error
        jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v1 com.vk.stat.sak.scheme.SchemeStatSak$TypeVkidEcosystemNavigationItem$Event[], still in use, count: 1, list:
          (r0v1 com.vk.stat.sak.scheme.SchemeStatSak$TypeVkidEcosystemNavigationItem$Event[]) from 0x0077: INVOKE (r0v1 com.vk.stat.sak.scheme.SchemeStatSak$TypeVkidEcosystemNavigationItem$Event[]) STATIC call: kotlin.enums.EnumEntriesKt.enumEntries(java.lang.Enum[]):kotlin.enums.EnumEntries A[MD:<E extends java.lang.Enum<E>>:(E extends java.lang.Enum<E>[]):kotlin.enums.EnumEntries<E extends java.lang.Enum<E>> (m), WRAPPED] (LINE:120)
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
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\f\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001j\u0002\b\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\f¨\u0006\r"}, d2 = {"Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeVkidEcosystemNavigationItem$Event;", "", "OPEN", "CLOSE", "TAP", "LOGOUT", "MULTIACC_ADD_ANOTHER_ACCOUNT_TAP", "SWITCH_ACCOUNT_TAP", "MULTIACC_DROP_ACCOUNT_TAP", "SECURITY_RECOMMENDATION_SHOW", "ERROR_API", "ERROR_SWITCHER", "THEME_CHANGED", "sak_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
        public static final class Event {
            OPEN,
            CLOSE,
            TAP,
            LOGOUT,
            MULTIACC_ADD_ANOTHER_ACCOUNT_TAP,
            SWITCH_ACCOUNT_TAP,
            MULTIACC_DROP_ACCOUNT_TAP,
            SECURITY_RECOMMENDATION_SHOW,
            ERROR_API,
            ERROR_SWITCHER,
            THEME_CHANGED;

            private static final /* synthetic */ EnumEntries kastatsbilkvmocb;

            static {
                kastatsbilkvmocb = EnumEntriesKt.enumEntries(eventArr);
            }

            private Event() {
                super(str, i);
            }

            @NotNull
            public static EnumEntries<Event> getEntries() {
                return kastatsbilkvmocb;
            }

            public static Event valueOf(String str) {
                return (Event) Enum.valueOf(Event.class, str);
            }

            public static Event[] values() {
                return (Event[]) kastatsbilkvmoca.clone();
            }
        }

        public TypeVkidEcosystemNavigationItem(@NotNull Event event, @NotNull String screen, @NotNull List<EcosystemNavigationOptionItem> options, @NotNull String metadata, @Nullable EcosystemNavigationItem ecosystemNavigationItem, @Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable Env env) {
            Intrinsics.checkNotNullParameter(event, "event");
            Intrinsics.checkNotNullParameter(screen, "screen");
            Intrinsics.checkNotNullParameter(options, "options");
            Intrinsics.checkNotNullParameter(metadata, "metadata");
            this.event = event;
            this.screen = screen;
            this.options = options;
            this.metadata = metadata;
            this.item = ecosystemNavigationItem;
            this.multiaccId = str;
            this.appLanguage = str2;
            this.osLanguage = str3;
            this.osCountry = str4;
            this.env = env;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ TypeVkidEcosystemNavigationItem copy$default(TypeVkidEcosystemNavigationItem typeVkidEcosystemNavigationItem, Event event, String str, List list, String str2, EcosystemNavigationItem ecosystemNavigationItem, String str3, String str4, String str5, String str6, Env env, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                event = typeVkidEcosystemNavigationItem.event;
            }
            if ((i10 & 2) != 0) {
                str = typeVkidEcosystemNavigationItem.screen;
            }
            if ((i10 & 4) != 0) {
                list = typeVkidEcosystemNavigationItem.options;
            }
            if ((i10 & 8) != 0) {
                str2 = typeVkidEcosystemNavigationItem.metadata;
            }
            if ((i10 & 16) != 0) {
                ecosystemNavigationItem = typeVkidEcosystemNavigationItem.item;
            }
            if ((i10 & 32) != 0) {
                str3 = typeVkidEcosystemNavigationItem.multiaccId;
            }
            if ((i10 & 64) != 0) {
                str4 = typeVkidEcosystemNavigationItem.appLanguage;
            }
            if ((i10 & 128) != 0) {
                str5 = typeVkidEcosystemNavigationItem.osLanguage;
            }
            if ((i10 & 256) != 0) {
                str6 = typeVkidEcosystemNavigationItem.osCountry;
            }
            if ((i10 & 512) != 0) {
                env = typeVkidEcosystemNavigationItem.env;
            }
            String str7 = str6;
            Env env2 = env;
            String str8 = str4;
            String str9 = str5;
            EcosystemNavigationItem ecosystemNavigationItem2 = ecosystemNavigationItem;
            String str10 = str3;
            return typeVkidEcosystemNavigationItem.copy(event, str, list, str2, ecosystemNavigationItem2, str10, str8, str9, str7, env2);
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Event getEvent() {
            return this.event;
        }

        @Nullable
        /* JADX INFO: renamed from: component10, reason: from getter */
        public final Env getEnv() {
            return this.env;
        }

        @NotNull
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getScreen() {
            return this.screen;
        }

        @NotNull
        public final List<EcosystemNavigationOptionItem> component3() {
            return this.options;
        }

        @NotNull
        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getMetadata() {
            return this.metadata;
        }

        @Nullable
        /* JADX INFO: renamed from: component5, reason: from getter */
        public final EcosystemNavigationItem getItem() {
            return this.item;
        }

        @Nullable
        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getMultiaccId() {
            return this.multiaccId;
        }

        @Nullable
        /* JADX INFO: renamed from: component7, reason: from getter */
        public final String getAppLanguage() {
            return this.appLanguage;
        }

        @Nullable
        /* JADX INFO: renamed from: component8, reason: from getter */
        public final String getOsLanguage() {
            return this.osLanguage;
        }

        @Nullable
        /* JADX INFO: renamed from: component9, reason: from getter */
        public final String getOsCountry() {
            return this.osCountry;
        }

        @NotNull
        public final TypeVkidEcosystemNavigationItem copy(@NotNull Event event, @NotNull String screen, @NotNull List<EcosystemNavigationOptionItem> options, @NotNull String metadata, @Nullable EcosystemNavigationItem item, @Nullable String multiaccId, @Nullable String appLanguage, @Nullable String osLanguage, @Nullable String osCountry, @Nullable Env env) {
            Intrinsics.checkNotNullParameter(event, "event");
            Intrinsics.checkNotNullParameter(screen, "screen");
            Intrinsics.checkNotNullParameter(options, "options");
            Intrinsics.checkNotNullParameter(metadata, "metadata");
            return new TypeVkidEcosystemNavigationItem(event, screen, options, metadata, item, multiaccId, appLanguage, osLanguage, osCountry, env);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof TypeVkidEcosystemNavigationItem)) {
                return false;
            }
            TypeVkidEcosystemNavigationItem typeVkidEcosystemNavigationItem = (TypeVkidEcosystemNavigationItem) other;
            return this.event == typeVkidEcosystemNavigationItem.event && Intrinsics.areEqual(this.screen, typeVkidEcosystemNavigationItem.screen) && Intrinsics.areEqual(this.options, typeVkidEcosystemNavigationItem.options) && Intrinsics.areEqual(this.metadata, typeVkidEcosystemNavigationItem.metadata) && this.item == typeVkidEcosystemNavigationItem.item && Intrinsics.areEqual(this.multiaccId, typeVkidEcosystemNavigationItem.multiaccId) && Intrinsics.areEqual(this.appLanguage, typeVkidEcosystemNavigationItem.appLanguage) && Intrinsics.areEqual(this.osLanguage, typeVkidEcosystemNavigationItem.osLanguage) && Intrinsics.areEqual(this.osCountry, typeVkidEcosystemNavigationItem.osCountry) && this.env == typeVkidEcosystemNavigationItem.env;
        }

        @Nullable
        public final String getAppLanguage() {
            return this.appLanguage;
        }

        @Nullable
        public final Env getEnv() {
            return this.env;
        }

        @NotNull
        public final Event getEvent() {
            return this.event;
        }

        @Nullable
        public final EcosystemNavigationItem getItem() {
            return this.item;
        }

        @NotNull
        public final String getMetadata() {
            return this.metadata;
        }

        @Nullable
        public final String getMultiaccId() {
            return this.multiaccId;
        }

        @NotNull
        public final List<EcosystemNavigationOptionItem> getOptions() {
            return this.options;
        }

        @Nullable
        public final String getOsCountry() {
            return this.osCountry;
        }

        @Nullable
        public final String getOsLanguage() {
            return this.osLanguage;
        }

        @NotNull
        public final String getScreen() {
            return this.screen;
        }

        public int hashCode() {
            int iHashCode = (this.metadata.hashCode() + ((this.options.hashCode() + ((this.screen.hashCode() + (this.event.hashCode() * 31)) * 31)) * 31)) * 31;
            EcosystemNavigationItem ecosystemNavigationItem = this.item;
            int iHashCode2 = (iHashCode + (ecosystemNavigationItem == null ? 0 : ecosystemNavigationItem.hashCode())) * 31;
            String str = this.multiaccId;
            int iHashCode3 = (iHashCode2 + (str == null ? 0 : str.hashCode())) * 31;
            String str2 = this.appLanguage;
            int iHashCode4 = (iHashCode3 + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.osLanguage;
            int iHashCode5 = (iHashCode4 + (str3 == null ? 0 : str3.hashCode())) * 31;
            String str4 = this.osCountry;
            int iHashCode6 = (iHashCode5 + (str4 == null ? 0 : str4.hashCode())) * 31;
            Env env = this.env;
            return iHashCode6 + (env != null ? env.hashCode() : 0);
        }

        @NotNull
        public String toString() {
            return "TypeVkidEcosystemNavigationItem(event=" + this.event + ", screen=" + this.screen + ", options=" + this.options + ", metadata=" + this.metadata + ", item=" + this.item + ", multiaccId=" + this.multiaccId + ", appLanguage=" + this.appLanguage + ", osLanguage=" + this.osLanguage + ", osCountry=" + this.osCountry + ", env=" + this.env + ')';
        }

        public /* synthetic */ TypeVkidEcosystemNavigationItem(Event event, String str, List list, String str2, EcosystemNavigationItem ecosystemNavigationItem, String str3, String str4, String str5, String str6, Env env, int i10, DefaultConstructorMarker defaultConstructorMarker) {
            this(event, str, list, str2, (i10 & 16) != 0 ? null : ecosystemNavigationItem, (i10 & 32) != 0 ? null : str3, (i10 & 64) != 0 ? null : str4, (i10 & 128) != 0 ? null : str5, (i10 & 256) != 0 ? null : str6, (i10 & 512) != 0 ? null : env);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes12.dex */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b!\b\u0086\b\u0018\u00002\u00020\u0001:\u0001MBu\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0004\u0012\u0006\u0010\t\u001a\u00020\u0004\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\f\u001a\u00020\u0002\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\n\u0012\u0010\b\u0002\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0010¢\u0006\u0004\b\u0013\u0010\u0014J\u0010\u0010\u0015\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0015\u0010\u0016J\u0010\u0010\u0017\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b\u0017\u0010\u0018J\u0010\u0010\u0019\u001a\u00020\u0006HÆ\u0003¢\u0006\u0004\b\u0019\u0010\u001aJ\u0010\u0010\u001b\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b\u001b\u0010\u0018J\u0010\u0010\u001c\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b\u001c\u0010\u0018J\u0010\u0010\u001d\u001a\u00020\nHÆ\u0003¢\u0006\u0004\b\u001d\u0010\u001eJ\u0010\u0010\u001f\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u001f\u0010\u0016J\u0012\u0010 \u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0004\b \u0010!J\u0012\u0010\"\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0004\b\"\u0010!J\u0012\u0010#\u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0004\b#\u0010$J\u0018\u0010%\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0010HÆ\u0003¢\u0006\u0004\b%\u0010&J\u008c\u0001\u0010'\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u00042\b\b\u0002\u0010\t\u001a\u00020\u00042\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\u00022\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\n2\u0010\b\u0002\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0010HÆ\u0001¢\u0006\u0004\b'\u0010(J\u0010\u0010)\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b)\u0010\u0016J\u0010\u0010*\u001a\u00020\nHÖ\u0001¢\u0006\u0004\b*\u0010\u001eJ\u001a\u0010.\u001a\u00020-2\b\u0010,\u001a\u0004\u0018\u00010+HÖ\u0003¢\u0006\u0004\b.\u0010/R\u001a\u0010\u0003\u001a\u00020\u00028\u0006X\u0087\u0004¢\u0006\f\n\u0004\b0\u00101\u001a\u0004\b2\u0010\u0016R\u001a\u0010\u0005\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\f\n\u0004\b3\u00104\u001a\u0004\b5\u0010\u0018R\u001a\u0010\u0007\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\f\n\u0004\b6\u00107\u001a\u0004\b8\u0010\u001aR\u001a\u0010\b\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\f\n\u0004\b9\u00104\u001a\u0004\b:\u0010\u0018R\u001a\u0010\t\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\f\n\u0004\b;\u00104\u001a\u0004\b<\u0010\u0018R\u001a\u0010\u000b\u001a\u00020\n8\u0006X\u0087\u0004¢\u0006\f\n\u0004\b=\u0010>\u001a\u0004\b?\u0010\u001eR\u001a\u0010\f\u001a\u00020\u00028\u0006X\u0087\u0004¢\u0006\f\n\u0004\b@\u00101\u001a\u0004\bA\u0010\u0016R\u001c\u0010\r\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004¢\u0006\f\n\u0004\bB\u0010C\u001a\u0004\bD\u0010!R\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004¢\u0006\f\n\u0004\bE\u0010C\u001a\u0004\bF\u0010!R\u001c\u0010\u000f\u001a\u0004\u0018\u00010\n8\u0006X\u0087\u0004¢\u0006\f\n\u0004\bG\u0010H\u001a\u0004\bI\u0010$R\"\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u00108\u0006X\u0087\u0004¢\u0006\f\n\u0004\bJ\u0010K\u001a\u0004\bL\u0010&¨\u0006N"}, d2 = {"Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeMultiaccountsItem;", "Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeAction$Payload;", "", "multiaccId", "", "multiaccRegTime", "Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeMultiaccountsItem$EventType;", "eventType", BlockstoreDeleteReceiver.PARAM_USER_ID, "prevUserId", "", "currentAccountsNum", "metadata", "masterUserId", "masterUserRegTime", "relatedCategory", "", "Lcom/vk/stat/sak/scheme/SchemeStatSak$MultiaccountFieldItem;", "fields", "<init>", "(Ljava/lang/String;JLcom/vk/stat/sak/scheme/SchemeStatSak$TypeMultiaccountsItem$EventType;JJILjava/lang/String;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Integer;Ljava/util/List;)V", "component1", "()Ljava/lang/String;", "component2", "()J", "component3", "()Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeMultiaccountsItem$EventType;", "component4", "component5", "component6", "()I", "component7", "component8", "()Ljava/lang/Long;", "component9", "component10", "()Ljava/lang/Integer;", "component11", "()Ljava/util/List;", "copy", "(Ljava/lang/String;JLcom/vk/stat/sak/scheme/SchemeStatSak$TypeMultiaccountsItem$EventType;JJILjava/lang/String;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Integer;Ljava/util/List;)Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeMultiaccountsItem;", "toString", "hashCode", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "kastatsbilkvmoca", "Ljava/lang/String;", "getMultiaccId", "kastatsbilkvmocb", "J", "getMultiaccRegTime", "kastatsbilkvmocc", "Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeMultiaccountsItem$EventType;", "getEventType", "kastatsbilkvmocd", "getUserId", "kastatsbilkvmoce", "getPrevUserId", "kastatsbilkvmocf", "I", "getCurrentAccountsNum", "kastatsbilkvmocg", "getMetadata", "kastatsbilkvmoch", "Ljava/lang/Long;", "getMasterUserId", "kastatsbilkvmoci", "getMasterUserRegTime", "kastatsbilkvmocj", "Ljava/lang/Integer;", "getRelatedCategory", "kastatsbilkvmock", "Ljava/util/List;", "getFields", "EventType", "sak_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final /* data */ class TypeMultiaccountsItem implements TypeAction.Payload {

        /* JADX INFO: renamed from: kastatsbilkvmoca, reason: from kotlin metadata */
        @SerializedName("multiacc_id")
        @NotNull
        private final String multiaccId;

        /* JADX INFO: renamed from: kastatsbilkvmocb, reason: from kotlin metadata */
        @SerializedName("multiacc_reg_time")
        private final long multiaccRegTime;

        /* JADX INFO: renamed from: kastatsbilkvmocc, reason: from kotlin metadata */
        @SerializedName(MailContract.EVENT_TYPE)
        @NotNull
        private final EventType eventType;

        /* JADX INFO: renamed from: kastatsbilkvmocd, reason: from kotlin metadata */
        @SerializedName("user_id")
        private final long userId;

        /* JADX INFO: renamed from: kastatsbilkvmoce, reason: from kotlin metadata */
        @SerializedName("prev_user_id")
        private final long prevUserId;

        /* JADX INFO: renamed from: kastatsbilkvmocf, reason: from kotlin metadata */
        @SerializedName("current_accounts_num")
        private final int currentAccountsNum;

        /* JADX INFO: renamed from: kastatsbilkvmocg, reason: from kotlin metadata */
        @SerializedName("metadata")
        @NotNull
        private final String metadata;

        /* JADX INFO: renamed from: kastatsbilkvmoch, reason: from kotlin metadata */
        @SerializedName("master_user_id")
        @Nullable
        private final Long masterUserId;

        /* JADX INFO: renamed from: kastatsbilkvmoci, reason: from kotlin metadata */
        @SerializedName("master_user_reg_time")
        @Nullable
        private final Long masterUserRegTime;

        /* JADX INFO: renamed from: kastatsbilkvmocj, reason: from kotlin metadata */
        @SerializedName("related_category")
        @Nullable
        private final Integer relatedCategory;

        /* JADX INFO: renamed from: kastatsbilkvmock, reason: from kotlin metadata */
        @SerializedName("fields")
        @Nullable
        private final List<MultiaccountFieldItem> fields;

        /* JADX WARN: Enum visitor error
        jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v18 com.vk.stat.sak.scheme.SchemeStatSak$TypeMultiaccountsItem$EventType[], still in use, count: 1, list:
          (r0v18 com.vk.stat.sak.scheme.SchemeStatSak$TypeMultiaccountsItem$EventType[]) from 0x00d6: INVOKE (r0v18 com.vk.stat.sak.scheme.SchemeStatSak$TypeMultiaccountsItem$EventType[]) STATIC call: kotlin.enums.EnumEntriesKt.enumEntries(java.lang.Enum[]):kotlin.enums.EnumEntries A[MD:<E extends java.lang.Enum<E>>:(E extends java.lang.Enum<E>[]):kotlin.enums.EnumEntries<E extends java.lang.Enum<E>> (m), WRAPPED] (LINE:215)
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
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0013\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001j\u0002\b\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013¨\u0006\u0014"}, d2 = {"Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeMultiaccountsItem$EventType;", "", "CREATE_MULTIACC", "CREATE_MULTIACC_SILENT", "ADD_ACCOUNT", "DROP_ACCOUNT", "SWITCH_FROM_SWITCHER", "SWITCH_FROM_PUSH", "SWITCH", "COMPLETE_ONBOARDING", "COMPLETE_ONBOARDING_LONG_TAP", "SWITCH_FROM_SWITCHER_SETTINGS_LOGOUT", "SWITCH_FROM_SWITCHER_PROFILE", "SWITCH_FROM_SWITCHER_LK_VKID", "SWITCH_FROM_SWITCHER_WEB_APP", "SWITCH_ADD_AUTH", "SWITCH_FROM_SWITCHER_SERVICES_MENU", "SWITCH_FROM_SWITCHER_SETTINGS", "SWITCH_FROM_SWITCHER_LONGTAP", "SWITCH_FROM_SWITCHER_SHARE_EXTERNAL", "sak_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
        public static final class EventType {
            CREATE_MULTIACC,
            CREATE_MULTIACC_SILENT,
            ADD_ACCOUNT,
            DROP_ACCOUNT,
            SWITCH_FROM_SWITCHER,
            SWITCH_FROM_PUSH,
            SWITCH,
            COMPLETE_ONBOARDING,
            COMPLETE_ONBOARDING_LONG_TAP,
            SWITCH_FROM_SWITCHER_SETTINGS_LOGOUT,
            SWITCH_FROM_SWITCHER_PROFILE,
            SWITCH_FROM_SWITCHER_LK_VKID,
            SWITCH_FROM_SWITCHER_WEB_APP,
            SWITCH_ADD_AUTH,
            SWITCH_FROM_SWITCHER_SERVICES_MENU,
            SWITCH_FROM_SWITCHER_SETTINGS,
            SWITCH_FROM_SWITCHER_LONGTAP,
            SWITCH_FROM_SWITCHER_SHARE_EXTERNAL;

            private static final /* synthetic */ EnumEntries kastatsbilkvmocb;

            static {
                kastatsbilkvmocb = EnumEntriesKt.enumEntries(eventTypeArr);
            }

            private EventType() {
                super(str, i);
            }

            @NotNull
            public static EnumEntries<EventType> getEntries() {
                return kastatsbilkvmocb;
            }

            public static EventType valueOf(String str) {
                return (EventType) Enum.valueOf(EventType.class, str);
            }

            public static EventType[] values() {
                return (EventType[]) kastatsbilkvmoca.clone();
            }
        }

        public TypeMultiaccountsItem(@NotNull String multiaccId, long j10, @NotNull EventType eventType, long j11, long j12, int i10, @NotNull String metadata, @Nullable Long l10, @Nullable Long l11, @Nullable Integer num, @Nullable List<MultiaccountFieldItem> list) {
            Intrinsics.checkNotNullParameter(multiaccId, "multiaccId");
            Intrinsics.checkNotNullParameter(eventType, "eventType");
            Intrinsics.checkNotNullParameter(metadata, "metadata");
            this.multiaccId = multiaccId;
            this.multiaccRegTime = j10;
            this.eventType = eventType;
            this.userId = j11;
            this.prevUserId = j12;
            this.currentAccountsNum = i10;
            this.metadata = metadata;
            this.masterUserId = l10;
            this.masterUserRegTime = l11;
            this.relatedCategory = num;
            this.fields = list;
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getMultiaccId() {
            return this.multiaccId;
        }

        @Nullable
        /* JADX INFO: renamed from: component10, reason: from getter */
        public final Integer getRelatedCategory() {
            return this.relatedCategory;
        }

        @Nullable
        public final List<MultiaccountFieldItem> component11() {
            return this.fields;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final long getMultiaccRegTime() {
            return this.multiaccRegTime;
        }

        @NotNull
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final EventType getEventType() {
            return this.eventType;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final long getUserId() {
            return this.userId;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final long getPrevUserId() {
            return this.prevUserId;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final int getCurrentAccountsNum() {
            return this.currentAccountsNum;
        }

        @NotNull
        /* JADX INFO: renamed from: component7, reason: from getter */
        public final String getMetadata() {
            return this.metadata;
        }

        @Nullable
        /* JADX INFO: renamed from: component8, reason: from getter */
        public final Long getMasterUserId() {
            return this.masterUserId;
        }

        @Nullable
        /* JADX INFO: renamed from: component9, reason: from getter */
        public final Long getMasterUserRegTime() {
            return this.masterUserRegTime;
        }

        @NotNull
        public final TypeMultiaccountsItem copy(@NotNull String multiaccId, long multiaccRegTime, @NotNull EventType eventType, long userId, long prevUserId, int currentAccountsNum, @NotNull String metadata, @Nullable Long masterUserId, @Nullable Long masterUserRegTime, @Nullable Integer relatedCategory, @Nullable List<MultiaccountFieldItem> fields) {
            Intrinsics.checkNotNullParameter(multiaccId, "multiaccId");
            Intrinsics.checkNotNullParameter(eventType, "eventType");
            Intrinsics.checkNotNullParameter(metadata, "metadata");
            return new TypeMultiaccountsItem(multiaccId, multiaccRegTime, eventType, userId, prevUserId, currentAccountsNum, metadata, masterUserId, masterUserRegTime, relatedCategory, fields);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof TypeMultiaccountsItem)) {
                return false;
            }
            TypeMultiaccountsItem typeMultiaccountsItem = (TypeMultiaccountsItem) other;
            return Intrinsics.areEqual(this.multiaccId, typeMultiaccountsItem.multiaccId) && this.multiaccRegTime == typeMultiaccountsItem.multiaccRegTime && this.eventType == typeMultiaccountsItem.eventType && this.userId == typeMultiaccountsItem.userId && this.prevUserId == typeMultiaccountsItem.prevUserId && this.currentAccountsNum == typeMultiaccountsItem.currentAccountsNum && Intrinsics.areEqual(this.metadata, typeMultiaccountsItem.metadata) && Intrinsics.areEqual(this.masterUserId, typeMultiaccountsItem.masterUserId) && Intrinsics.areEqual(this.masterUserRegTime, typeMultiaccountsItem.masterUserRegTime) && Intrinsics.areEqual(this.relatedCategory, typeMultiaccountsItem.relatedCategory) && Intrinsics.areEqual(this.fields, typeMultiaccountsItem.fields);
        }

        public final int getCurrentAccountsNum() {
            return this.currentAccountsNum;
        }

        @NotNull
        public final EventType getEventType() {
            return this.eventType;
        }

        @Nullable
        public final List<MultiaccountFieldItem> getFields() {
            return this.fields;
        }

        @Nullable
        public final Long getMasterUserId() {
            return this.masterUserId;
        }

        @Nullable
        public final Long getMasterUserRegTime() {
            return this.masterUserRegTime;
        }

        @NotNull
        public final String getMetadata() {
            return this.metadata;
        }

        @NotNull
        public final String getMultiaccId() {
            return this.multiaccId;
        }

        public final long getMultiaccRegTime() {
            return this.multiaccRegTime;
        }

        public final long getPrevUserId() {
            return this.prevUserId;
        }

        @Nullable
        public final Integer getRelatedCategory() {
            return this.relatedCategory;
        }

        public final long getUserId() {
            return this.userId;
        }

        public int hashCode() {
            int iHashCode = (this.metadata.hashCode() + ((Integer.hashCode(this.currentAccountsNum) + ((Long.hashCode(this.prevUserId) + ((Long.hashCode(this.userId) + ((this.eventType.hashCode() + ((Long.hashCode(this.multiaccRegTime) + (this.multiaccId.hashCode() * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31;
            Long l10 = this.masterUserId;
            int iHashCode2 = (iHashCode + (l10 == null ? 0 : l10.hashCode())) * 31;
            Long l11 = this.masterUserRegTime;
            int iHashCode3 = (iHashCode2 + (l11 == null ? 0 : l11.hashCode())) * 31;
            Integer num = this.relatedCategory;
            int iHashCode4 = (iHashCode3 + (num == null ? 0 : num.hashCode())) * 31;
            List<MultiaccountFieldItem> list = this.fields;
            return iHashCode4 + (list != null ? list.hashCode() : 0);
        }

        @NotNull
        public String toString() {
            return "TypeMultiaccountsItem(multiaccId=" + this.multiaccId + ", multiaccRegTime=" + this.multiaccRegTime + ", eventType=" + this.eventType + ", userId=" + this.userId + ", prevUserId=" + this.prevUserId + ", currentAccountsNum=" + this.currentAccountsNum + ", metadata=" + this.metadata + ", masterUserId=" + this.masterUserId + ", masterUserRegTime=" + this.masterUserRegTime + ", relatedCategory=" + this.relatedCategory + ", fields=" + this.fields + ')';
        }

        public /* synthetic */ TypeMultiaccountsItem(String str, long j10, EventType eventType, long j11, long j12, int i10, String str2, Long l10, Long l11, Integer num, List list, int i11, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, j10, eventType, j11, j12, i10, str2, (i11 & 128) != 0 ? null : l10, (i11 & 256) != 0 ? null : l11, (i11 & 512) != 0 ? null : num, (i11 & 1024) != 0 ? null : list);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes12.dex */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b \n\u0002\u0010\u0000\n\u0002\b%\b\u0086\b\u0018\u00002\u00020\u0001:\u0001SB«\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\f\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000e\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0015\u0010\u0016J\u0010\u0010\u0017\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0017\u0010\u0018J\u0012\u0010\u0019\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0004\b\u0019\u0010\u001aJ\u0012\u0010\u001b\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0004\b\u001b\u0010\u001cJ\u0012\u0010\u001d\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0004\b\u001d\u0010\u001aJ\u0012\u0010\u001e\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0004\b\u001e\u0010\u001cJ\u0012\u0010\u001f\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0004\b\u001f\u0010\u001aJ\u0012\u0010 \u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0004\b \u0010\u001aJ\u0012\u0010!\u001a\u0004\u0018\u00010\fHÆ\u0003¢\u0006\u0004\b!\u0010\"J\u0012\u0010#\u001a\u0004\u0018\u00010\u000eHÆ\u0003¢\u0006\u0004\b#\u0010$J\u0012\u0010%\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0004\b%\u0010\u001aJ\u0012\u0010&\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0004\b&\u0010\u001aJ\u0012\u0010'\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0004\b'\u0010\u001cJ\u0012\u0010(\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0004\b(\u0010\u001aJ\u0012\u0010)\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0004\b)\u0010\u001aJ¶\u0001\u0010*\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0004HÆ\u0001¢\u0006\u0004\b*\u0010+J\u0010\u0010,\u001a\u00020\u0004HÖ\u0001¢\u0006\u0004\b,\u0010\u001aJ\u0010\u0010-\u001a\u00020\u0006HÖ\u0001¢\u0006\u0004\b-\u0010.J\u001a\u00101\u001a\u00020\u000e2\b\u00100\u001a\u0004\u0018\u00010/HÖ\u0003¢\u0006\u0004\b1\u00102R\u001a\u0010\u0003\u001a\u00020\u00028\u0006X\u0087\u0004¢\u0006\f\n\u0004\b3\u00104\u001a\u0004\b5\u0010\u0018R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004¢\u0006\f\n\u0004\b6\u00107\u001a\u0004\b8\u0010\u001aR\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\f\n\u0004\b9\u0010:\u001a\u0004\b;\u0010\u001cR\u001c\u0010\b\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004¢\u0006\f\n\u0004\b<\u00107\u001a\u0004\b=\u0010\u001aR\u001c\u0010\t\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\f\n\u0004\b>\u0010:\u001a\u0004\b?\u0010\u001cR\u001c\u0010\n\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004¢\u0006\f\n\u0004\b@\u00107\u001a\u0004\bA\u0010\u001aR\u001c\u0010\u000b\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004¢\u0006\f\n\u0004\bB\u00107\u001a\u0004\bC\u0010\u001aR\u001c\u0010\r\u001a\u0004\u0018\u00010\f8\u0006X\u0087\u0004¢\u0006\f\n\u0004\bD\u0010E\u001a\u0004\bF\u0010\"R\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u000e8\u0006X\u0087\u0004¢\u0006\f\n\u0004\bG\u0010H\u001a\u0004\b\u000f\u0010$R\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004¢\u0006\f\n\u0004\bI\u00107\u001a\u0004\bJ\u0010\u001aR\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004¢\u0006\f\n\u0004\bK\u00107\u001a\u0004\bL\u0010\u001aR\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\f\n\u0004\bM\u0010:\u001a\u0004\bN\u0010\u001cR\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004¢\u0006\f\n\u0004\bO\u00107\u001a\u0004\bP\u0010\u001aR\u001c\u0010\u0014\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004¢\u0006\f\n\u0004\bQ\u00107\u001a\u0004\bR\u0010\u001a¨\u0006T"}, d2 = {"Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeVkPayCheckoutItem;", "Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeAction$Payload;", "Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeVkPayCheckoutItem$EventType;", "eventType", "", "unauthId", "", "paymentMethodsCount", "paymentMethods", "parentAppId", "transactionType", "transactionItem", "", "sessionId", "", "isFailed", "failReason", "orderId", BillingFlowParams.EXTRA_PARAM_KEY_ACCOUNT_ID, "accountInfo", "transactionId", "<init>", "(Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeVkPayCheckoutItem$EventType;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;)V", "component1", "()Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeVkPayCheckoutItem$EventType;", "component2", "()Ljava/lang/String;", "component3", "()Ljava/lang/Integer;", "component4", "component5", "component6", "component7", "component8", "()Ljava/lang/Long;", "component9", "()Ljava/lang/Boolean;", "component10", "component11", "component12", "component13", "component14", "copy", "(Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeVkPayCheckoutItem$EventType;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;)Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeVkPayCheckoutItem;", "toString", "hashCode", "()I", "", "other", "equals", "(Ljava/lang/Object;)Z", "kastatsbilkvmoca", "Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeVkPayCheckoutItem$EventType;", "getEventType", "kastatsbilkvmocb", "Ljava/lang/String;", "getUnauthId", "kastatsbilkvmocc", "Ljava/lang/Integer;", "getPaymentMethodsCount", "kastatsbilkvmocd", "getPaymentMethods", "kastatsbilkvmoce", "getParentAppId", "kastatsbilkvmocf", "getTransactionType", "kastatsbilkvmocg", "getTransactionItem", "kastatsbilkvmoch", "Ljava/lang/Long;", "getSessionId", "kastatsbilkvmoci", "Ljava/lang/Boolean;", "kastatsbilkvmocj", "getFailReason", "kastatsbilkvmock", "getOrderId", "kastatsbilkvmocl", "getAccountId", "kastatsbilkvmocm", "getAccountInfo", "kastatsbilkvmocn", "getTransactionId", "EventType", "sak_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final /* data */ class TypeVkPayCheckoutItem implements TypeAction.Payload {

        /* JADX INFO: renamed from: kastatsbilkvmoca, reason: from kotlin metadata */
        @SerializedName(MailContract.EVENT_TYPE)
        @NotNull
        private final EventType eventType;

        /* JADX INFO: renamed from: kastatsbilkvmocb, reason: from kotlin metadata */
        @SerializedName("unauth_id")
        @Nullable
        private final String unauthId;

        /* JADX INFO: renamed from: kastatsbilkvmocc, reason: from kotlin metadata */
        @SerializedName("payment_methods_count")
        @Nullable
        private final Integer paymentMethodsCount;

        /* JADX INFO: renamed from: kastatsbilkvmocd, reason: from kotlin metadata */
        @SerializedName("payment_methods")
        @Nullable
        private final String paymentMethods;

        /* JADX INFO: renamed from: kastatsbilkvmoce, reason: from kotlin metadata */
        @SerializedName(RegistrationStatParamsFactory.PARENT_APP_ID)
        @Nullable
        private final Integer parentAppId;

        /* JADX INFO: renamed from: kastatsbilkvmocf, reason: from kotlin metadata */
        @SerializedName("transaction_type")
        @Nullable
        private final String transactionType;

        /* JADX INFO: renamed from: kastatsbilkvmocg, reason: from kotlin metadata */
        @SerializedName("transaction_item")
        @Nullable
        private final String transactionItem;

        /* JADX INFO: renamed from: kastatsbilkvmoch, reason: from kotlin metadata */
        @SerializedName(EventParams.SESSION_ID)
        @Nullable
        private final Long sessionId;

        /* JADX INFO: renamed from: kastatsbilkvmoci, reason: from kotlin metadata */
        @SerializedName("is_failed")
        @Nullable
        private final Boolean isFailed;

        /* JADX INFO: renamed from: kastatsbilkvmocj, reason: from kotlin metadata */
        @SerializedName("fail_reason")
        @Nullable
        private final String failReason;

        /* JADX INFO: renamed from: kastatsbilkvmock, reason: from kotlin metadata */
        @SerializedName("order_id")
        @Nullable
        private final String orderId;

        /* JADX INFO: renamed from: kastatsbilkvmocl, reason: from kotlin metadata */
        @SerializedName(NotificationUpdater.EXTRA_ACCOUNT_ID)
        @Nullable
        private final Integer accountId;

        /* JADX INFO: renamed from: kastatsbilkvmocm, reason: from kotlin metadata */
        @SerializedName("account_info")
        @Nullable
        private final String accountInfo;

        /* JADX INFO: renamed from: kastatsbilkvmocn, reason: from kotlin metadata */
        @SerializedName("transaction_id")
        @Nullable
        private final String transactionId;

        /* JADX WARN: Enum visitor error
        jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v20 com.vk.stat.sak.scheme.SchemeStatSak$TypeVkPayCheckoutItem$EventType[], still in use, count: 1, list:
          (r0v20 com.vk.stat.sak.scheme.SchemeStatSak$TypeVkPayCheckoutItem$EventType[]) from 0x00f4: INVOKE (r0v20 com.vk.stat.sak.scheme.SchemeStatSak$TypeVkPayCheckoutItem$EventType[]) STATIC call: kotlin.enums.EnumEntriesKt.enumEntries(java.lang.Enum[]):kotlin.enums.EnumEntries A[MD:<E extends java.lang.Enum<E>>:(E extends java.lang.Enum<E>[]):kotlin.enums.EnumEntries<E extends java.lang.Enum<E>> (m), WRAPPED] (LINE:245)
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
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0015\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001j\u0002\b\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015¨\u0006\u0016"}, d2 = {"Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeVkPayCheckoutItem$EventType;", "", "START_SESSION", "SHOW_INSTANT_PAY_BOX", "SHOW_FULL_PAY_BOX", "DELETE_PS", "CREATE_VK_PAY_WALLET", "NEW_WALLET_ACCEPT", "ADD_NEW_PS", "NEW_CARD_ACCEPT", "CHOOSE_PS", "PAYMENT_CONFIRMATION", "INIT_TRANSACTION", "ACCESS_BLOCKED", "ACCESS_RESTORE", "SMS_SEND", "NEW_PIN", "CHARGE_MONEY", "DELIVER_ORDER", "COMPLETE_SESSION", "SUCCESS", "FAILED", "sak_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
        public static final class EventType {
            START_SESSION,
            SHOW_INSTANT_PAY_BOX,
            SHOW_FULL_PAY_BOX,
            DELETE_PS,
            CREATE_VK_PAY_WALLET,
            NEW_WALLET_ACCEPT,
            ADD_NEW_PS,
            NEW_CARD_ACCEPT,
            CHOOSE_PS,
            PAYMENT_CONFIRMATION,
            INIT_TRANSACTION,
            ACCESS_BLOCKED,
            ACCESS_RESTORE,
            SMS_SEND,
            NEW_PIN,
            CHARGE_MONEY,
            DELIVER_ORDER,
            COMPLETE_SESSION,
            SUCCESS,
            FAILED;

            private static final /* synthetic */ EnumEntries kastatsbilkvmocb;

            static {
                kastatsbilkvmocb = EnumEntriesKt.enumEntries(eventTypeArr);
            }

            private EventType() {
                super(str, i);
            }

            @NotNull
            public static EnumEntries<EventType> getEntries() {
                return kastatsbilkvmocb;
            }

            public static EventType valueOf(String str) {
                return (EventType) Enum.valueOf(EventType.class, str);
            }

            public static EventType[] values() {
                return (EventType[]) kastatsbilkvmoca.clone();
            }
        }

        public TypeVkPayCheckoutItem(@NotNull EventType eventType, @Nullable String str, @Nullable Integer num, @Nullable String str2, @Nullable Integer num2, @Nullable String str3, @Nullable String str4, @Nullable Long l10, @Nullable Boolean bool, @Nullable String str5, @Nullable String str6, @Nullable Integer num3, @Nullable String str7, @Nullable String str8) {
            Intrinsics.checkNotNullParameter(eventType, "eventType");
            this.eventType = eventType;
            this.unauthId = str;
            this.paymentMethodsCount = num;
            this.paymentMethods = str2;
            this.parentAppId = num2;
            this.transactionType = str3;
            this.transactionItem = str4;
            this.sessionId = l10;
            this.isFailed = bool;
            this.failReason = str5;
            this.orderId = str6;
            this.accountId = num3;
            this.accountInfo = str7;
            this.transactionId = str8;
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final EventType getEventType() {
            return this.eventType;
        }

        @Nullable
        /* JADX INFO: renamed from: component10, reason: from getter */
        public final String getFailReason() {
            return this.failReason;
        }

        @Nullable
        /* JADX INFO: renamed from: component11, reason: from getter */
        public final String getOrderId() {
            return this.orderId;
        }

        @Nullable
        /* JADX INFO: renamed from: component12, reason: from getter */
        public final Integer getAccountId() {
            return this.accountId;
        }

        @Nullable
        /* JADX INFO: renamed from: component13, reason: from getter */
        public final String getAccountInfo() {
            return this.accountInfo;
        }

        @Nullable
        /* JADX INFO: renamed from: component14, reason: from getter */
        public final String getTransactionId() {
            return this.transactionId;
        }

        @Nullable
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getUnauthId() {
            return this.unauthId;
        }

        @Nullable
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final Integer getPaymentMethodsCount() {
            return this.paymentMethodsCount;
        }

        @Nullable
        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getPaymentMethods() {
            return this.paymentMethods;
        }

        @Nullable
        /* JADX INFO: renamed from: component5, reason: from getter */
        public final Integer getParentAppId() {
            return this.parentAppId;
        }

        @Nullable
        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getTransactionType() {
            return this.transactionType;
        }

        @Nullable
        /* JADX INFO: renamed from: component7, reason: from getter */
        public final String getTransactionItem() {
            return this.transactionItem;
        }

        @Nullable
        /* JADX INFO: renamed from: component8, reason: from getter */
        public final Long getSessionId() {
            return this.sessionId;
        }

        @Nullable
        /* JADX INFO: renamed from: component9, reason: from getter */
        public final Boolean getIsFailed() {
            return this.isFailed;
        }

        @NotNull
        public final TypeVkPayCheckoutItem copy(@NotNull EventType eventType, @Nullable String unauthId, @Nullable Integer paymentMethodsCount, @Nullable String paymentMethods, @Nullable Integer parentAppId, @Nullable String transactionType, @Nullable String transactionItem, @Nullable Long sessionId, @Nullable Boolean isFailed, @Nullable String failReason, @Nullable String orderId, @Nullable Integer accountId, @Nullable String accountInfo, @Nullable String transactionId) {
            Intrinsics.checkNotNullParameter(eventType, "eventType");
            return new TypeVkPayCheckoutItem(eventType, unauthId, paymentMethodsCount, paymentMethods, parentAppId, transactionType, transactionItem, sessionId, isFailed, failReason, orderId, accountId, accountInfo, transactionId);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof TypeVkPayCheckoutItem)) {
                return false;
            }
            TypeVkPayCheckoutItem typeVkPayCheckoutItem = (TypeVkPayCheckoutItem) other;
            return this.eventType == typeVkPayCheckoutItem.eventType && Intrinsics.areEqual(this.unauthId, typeVkPayCheckoutItem.unauthId) && Intrinsics.areEqual(this.paymentMethodsCount, typeVkPayCheckoutItem.paymentMethodsCount) && Intrinsics.areEqual(this.paymentMethods, typeVkPayCheckoutItem.paymentMethods) && Intrinsics.areEqual(this.parentAppId, typeVkPayCheckoutItem.parentAppId) && Intrinsics.areEqual(this.transactionType, typeVkPayCheckoutItem.transactionType) && Intrinsics.areEqual(this.transactionItem, typeVkPayCheckoutItem.transactionItem) && Intrinsics.areEqual(this.sessionId, typeVkPayCheckoutItem.sessionId) && Intrinsics.areEqual(this.isFailed, typeVkPayCheckoutItem.isFailed) && Intrinsics.areEqual(this.failReason, typeVkPayCheckoutItem.failReason) && Intrinsics.areEqual(this.orderId, typeVkPayCheckoutItem.orderId) && Intrinsics.areEqual(this.accountId, typeVkPayCheckoutItem.accountId) && Intrinsics.areEqual(this.accountInfo, typeVkPayCheckoutItem.accountInfo) && Intrinsics.areEqual(this.transactionId, typeVkPayCheckoutItem.transactionId);
        }

        @Nullable
        public final Integer getAccountId() {
            return this.accountId;
        }

        @Nullable
        public final String getAccountInfo() {
            return this.accountInfo;
        }

        @NotNull
        public final EventType getEventType() {
            return this.eventType;
        }

        @Nullable
        public final String getFailReason() {
            return this.failReason;
        }

        @Nullable
        public final String getOrderId() {
            return this.orderId;
        }

        @Nullable
        public final Integer getParentAppId() {
            return this.parentAppId;
        }

        @Nullable
        public final String getPaymentMethods() {
            return this.paymentMethods;
        }

        @Nullable
        public final Integer getPaymentMethodsCount() {
            return this.paymentMethodsCount;
        }

        @Nullable
        public final Long getSessionId() {
            return this.sessionId;
        }

        @Nullable
        public final String getTransactionId() {
            return this.transactionId;
        }

        @Nullable
        public final String getTransactionItem() {
            return this.transactionItem;
        }

        @Nullable
        public final String getTransactionType() {
            return this.transactionType;
        }

        @Nullable
        public final String getUnauthId() {
            return this.unauthId;
        }

        public int hashCode() {
            int iHashCode = this.eventType.hashCode() * 31;
            String str = this.unauthId;
            int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
            Integer num = this.paymentMethodsCount;
            int iHashCode3 = (iHashCode2 + (num == null ? 0 : num.hashCode())) * 31;
            String str2 = this.paymentMethods;
            int iHashCode4 = (iHashCode3 + (str2 == null ? 0 : str2.hashCode())) * 31;
            Integer num2 = this.parentAppId;
            int iHashCode5 = (iHashCode4 + (num2 == null ? 0 : num2.hashCode())) * 31;
            String str3 = this.transactionType;
            int iHashCode6 = (iHashCode5 + (str3 == null ? 0 : str3.hashCode())) * 31;
            String str4 = this.transactionItem;
            int iHashCode7 = (iHashCode6 + (str4 == null ? 0 : str4.hashCode())) * 31;
            Long l10 = this.sessionId;
            int iHashCode8 = (iHashCode7 + (l10 == null ? 0 : l10.hashCode())) * 31;
            Boolean bool = this.isFailed;
            int iHashCode9 = (iHashCode8 + (bool == null ? 0 : bool.hashCode())) * 31;
            String str5 = this.failReason;
            int iHashCode10 = (iHashCode9 + (str5 == null ? 0 : str5.hashCode())) * 31;
            String str6 = this.orderId;
            int iHashCode11 = (iHashCode10 + (str6 == null ? 0 : str6.hashCode())) * 31;
            Integer num3 = this.accountId;
            int iHashCode12 = (iHashCode11 + (num3 == null ? 0 : num3.hashCode())) * 31;
            String str7 = this.accountInfo;
            int iHashCode13 = (iHashCode12 + (str7 == null ? 0 : str7.hashCode())) * 31;
            String str8 = this.transactionId;
            return iHashCode13 + (str8 != null ? str8.hashCode() : 0);
        }

        @Nullable
        public final Boolean isFailed() {
            return this.isFailed;
        }

        @NotNull
        public String toString() {
            return "TypeVkPayCheckoutItem(eventType=" + this.eventType + ", unauthId=" + this.unauthId + ", paymentMethodsCount=" + this.paymentMethodsCount + ", paymentMethods=" + this.paymentMethods + ", parentAppId=" + this.parentAppId + ", transactionType=" + this.transactionType + ", transactionItem=" + this.transactionItem + ", sessionId=" + this.sessionId + ", isFailed=" + this.isFailed + ", failReason=" + this.failReason + ", orderId=" + this.orderId + ", accountId=" + this.accountId + ", accountInfo=" + this.accountInfo + ", transactionId=" + this.transactionId + ')';
        }

        public /* synthetic */ TypeVkPayCheckoutItem(EventType eventType, String str, Integer num, String str2, Integer num2, String str3, String str4, Long l10, Boolean bool, String str5, String str6, Integer num3, String str7, String str8, int i10, DefaultConstructorMarker defaultConstructorMarker) {
            this(eventType, (i10 & 2) != 0 ? null : str, (i10 & 4) != 0 ? null : num, (i10 & 8) != 0 ? null : str2, (i10 & 16) != 0 ? null : num2, (i10 & 32) != 0 ? null : str3, (i10 & 64) != 0 ? null : str4, (i10 & 128) != 0 ? null : l10, (i10 & 256) != 0 ? null : bool, (i10 & 512) != 0 ? null : str5, (i10 & 1024) != 0 ? null : str6, (i10 & 2048) != 0 ? null : num3, (i10 & 4096) != 0 ? null : str7, (i10 & 8192) != 0 ? null : str8);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes12.dex */
    @Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\"\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b,\b\u0086\b\u0018\u00002\u00020\u0001:\u0002deBÉ\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u000b\u0012\u0010\b\u0002\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0010\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0015\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u001a\u0010\u001bJ\u0010\u0010\u001c\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u001c\u0010\u001dJ\u0012\u0010\u001e\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0004\b\u001e\u0010\u001fJ\u0012\u0010 \u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0004\b \u0010!J\u0012\u0010\"\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0004\b\"\u0010\u001fJ\u0012\u0010#\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0004\b#\u0010\u001fJ\u0012\u0010$\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0004\b$\u0010\u001fJ\u0012\u0010%\u001a\u0004\u0018\u00010\u000bHÆ\u0003¢\u0006\u0004\b%\u0010&J\u0018\u0010'\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\rHÆ\u0003¢\u0006\u0004\b'\u0010(J\u0012\u0010)\u001a\u0004\u0018\u00010\u0010HÆ\u0003¢\u0006\u0004\b)\u0010*J\u0012\u0010+\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0004\b+\u0010!J\u0012\u0010,\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0004\b,\u0010\u001fJ\u0012\u0010-\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0004\b-\u0010\u001fJ\u0012\u0010.\u001a\u0004\u0018\u00010\u0015HÆ\u0003¢\u0006\u0004\b.\u0010/J\u0012\u00100\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0004\b0\u0010!J\u0012\u00101\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0004\b1\u0010!J\u0012\u00102\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0004\b2\u0010!JÔ\u0001\u00103\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u000b2\u0010\b\u0002\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00152\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0006HÆ\u0001¢\u0006\u0004\b3\u00104J\u0010\u00105\u001a\u00020\u0004HÖ\u0001¢\u0006\u0004\b5\u0010\u001fJ\u0010\u00106\u001a\u00020\u0006HÖ\u0001¢\u0006\u0004\b6\u00107J\u001a\u0010;\u001a\u00020:2\b\u00109\u001a\u0004\u0018\u000108HÖ\u0003¢\u0006\u0004\b;\u0010<R\u001a\u0010\u0003\u001a\u00020\u00028\u0006X\u0087\u0004¢\u0006\f\n\u0004\b=\u0010>\u001a\u0004\b?\u0010\u001dR\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004¢\u0006\f\n\u0004\b@\u0010A\u001a\u0004\bB\u0010\u001fR\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\f\n\u0004\bC\u0010D\u001a\u0004\bE\u0010!R\u001c\u0010\b\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004¢\u0006\f\n\u0004\bF\u0010A\u001a\u0004\bG\u0010\u001fR\u001c\u0010\t\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004¢\u0006\f\n\u0004\bH\u0010A\u001a\u0004\bI\u0010\u001fR\u001c\u0010\n\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004¢\u0006\f\n\u0004\bJ\u0010A\u001a\u0004\bK\u0010\u001fR\u001c\u0010\f\u001a\u0004\u0018\u00010\u000b8\u0006X\u0087\u0004¢\u0006\f\n\u0004\bL\u0010M\u001a\u0004\bN\u0010&R\"\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r8\u0006X\u0087\u0004¢\u0006\f\n\u0004\bO\u0010P\u001a\u0004\bQ\u0010(R\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u00108\u0006X\u0087\u0004¢\u0006\f\n\u0004\bR\u0010S\u001a\u0004\bT\u0010*R\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\f\n\u0004\bU\u0010D\u001a\u0004\bV\u0010!R\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004¢\u0006\f\n\u0004\bW\u0010A\u001a\u0004\bX\u0010\u001fR\u001c\u0010\u0014\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004¢\u0006\f\n\u0004\bY\u0010A\u001a\u0004\bZ\u0010\u001fR\u001c\u0010\u0016\u001a\u0004\u0018\u00010\u00158\u0006X\u0087\u0004¢\u0006\f\n\u0004\b[\u0010\\\u001a\u0004\b]\u0010/R\u001c\u0010\u0017\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\f\n\u0004\b^\u0010D\u001a\u0004\b_\u0010!R\u001c\u0010\u0018\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\f\n\u0004\b`\u0010D\u001a\u0004\ba\u0010!R\u001c\u0010\u0019\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\f\n\u0004\bb\u0010D\u001a\u0004\bc\u0010!¨\u0006f"}, d2 = {"Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeRegistrationItem;", "Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeAction$Payload;", "Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeRegistrationItem$EventType;", "eventType", "", PasskeyBeginResult.SID_KEY, "", "clientId", "silentToken", "silentTokenUuid", "multiaccId", "", BlockstoreDeleteReceiver.PARAM_USER_ID, "", "Lcom/vk/stat/sak/scheme/SchemeStatSak$RegistrationFieldItem;", "fields", "Lcom/vk/stat/sak/scheme/SchemeStatSak$EventScreen;", "screenTo", "errorSubcode", "flowSource", "flowEntryPoints", "Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeRegistrationItem$Error;", "error", "authProviders", "appId", "authAppId", "<init>", "(Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeRegistrationItem$EventType;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/util/List;Lcom/vk/stat/sak/scheme/SchemeStatSak$EventScreen;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeRegistrationItem$Error;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)V", "component1", "()Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeRegistrationItem$EventType;", "component2", "()Ljava/lang/String;", "component3", "()Ljava/lang/Integer;", "component4", "component5", "component6", "component7", "()Ljava/lang/Long;", "component8", "()Ljava/util/List;", "component9", "()Lcom/vk/stat/sak/scheme/SchemeStatSak$EventScreen;", "component10", "component11", "component12", "component13", "()Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeRegistrationItem$Error;", "component14", "component15", "component16", "copy", "(Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeRegistrationItem$EventType;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/util/List;Lcom/vk/stat/sak/scheme/SchemeStatSak$EventScreen;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeRegistrationItem$Error;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeRegistrationItem;", "toString", "hashCode", "()I", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "kastatsbilkvmoca", "Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeRegistrationItem$EventType;", "getEventType", "kastatsbilkvmocb", "Ljava/lang/String;", "getSid", "kastatsbilkvmocc", "Ljava/lang/Integer;", "getClientId", "kastatsbilkvmocd", "getSilentToken", "kastatsbilkvmoce", "getSilentTokenUuid", "kastatsbilkvmocf", "getMultiaccId", "kastatsbilkvmocg", "Ljava/lang/Long;", "getUserId", "kastatsbilkvmoch", "Ljava/util/List;", "getFields", "kastatsbilkvmoci", "Lcom/vk/stat/sak/scheme/SchemeStatSak$EventScreen;", "getScreenTo", "kastatsbilkvmocj", "getErrorSubcode", "kastatsbilkvmock", "getFlowSource", "kastatsbilkvmocl", "getFlowEntryPoints", "kastatsbilkvmocm", "Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeRegistrationItem$Error;", "getError", "kastatsbilkvmocn", "getAuthProviders", "kastatsbilkvmoco", "getAppId", "kastatsbilkvmocp", "getAuthAppId", "EventType", "Error", "sak_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final /* data */ class TypeRegistrationItem implements TypeAction.Payload {

        /* JADX INFO: renamed from: kastatsbilkvmoca, reason: from kotlin metadata */
        @SerializedName(MailContract.EVENT_TYPE)
        @NotNull
        private final EventType eventType;

        /* JADX INFO: renamed from: kastatsbilkvmocb, reason: from kotlin metadata */
        @SerializedName(PasskeyBeginResult.SID_KEY)
        @Nullable
        private final String sid;

        /* JADX INFO: renamed from: kastatsbilkvmocc, reason: from kotlin metadata */
        @SerializedName("client_id")
        @Nullable
        private final Integer clientId;

        /* JADX INFO: renamed from: kastatsbilkvmocd, reason: from kotlin metadata */
        @SerializedName("silent_token")
        @Nullable
        private final String silentToken;

        /* JADX INFO: renamed from: kastatsbilkvmoce, reason: from kotlin metadata */
        @SerializedName("silent_token_uuid")
        @Nullable
        private final String silentTokenUuid;

        /* JADX INFO: renamed from: kastatsbilkvmocf, reason: from kotlin metadata */
        @SerializedName("multiacc_id")
        @Nullable
        private final String multiaccId;

        /* JADX INFO: renamed from: kastatsbilkvmocg, reason: from kotlin metadata */
        @SerializedName("user_id")
        @Nullable
        private final Long userId;

        /* JADX INFO: renamed from: kastatsbilkvmoch, reason: from kotlin metadata */
        @SerializedName("fields")
        @Nullable
        private final List<RegistrationFieldItem> fields;

        /* JADX INFO: renamed from: kastatsbilkvmoci, reason: from kotlin metadata */
        @SerializedName("screen_to")
        @Nullable
        private final EventScreen screenTo;

        /* JADX INFO: renamed from: kastatsbilkvmocj, reason: from kotlin metadata */
        @SerializedName("error_subcode")
        @Nullable
        private final Integer errorSubcode;

        /* JADX INFO: renamed from: kastatsbilkvmock, reason: from kotlin metadata */
        @SerializedName(RegistrationStatParamsFactory.FLOW_SOURCE)
        @Nullable
        private final String flowSource;

        /* JADX INFO: renamed from: kastatsbilkvmocl, reason: from kotlin metadata */
        @SerializedName(RegistrationStatParamsFactory.FLOW_ENTRY_POINTS)
        @Nullable
        private final String flowEntryPoints;

        /* JADX INFO: renamed from: kastatsbilkvmocm, reason: from kotlin metadata */
        @SerializedName("error")
        @Nullable
        private final Error error;

        /* JADX INFO: renamed from: kastatsbilkvmocn, reason: from kotlin metadata */
        @SerializedName("auth_providers")
        @Nullable
        private final Integer authProviders;

        /* JADX INFO: renamed from: kastatsbilkvmoco, reason: from kotlin metadata */
        @SerializedName("app_id")
        @Nullable
        private final Integer appId;

        /* JADX INFO: renamed from: kastatsbilkvmocp, reason: from kotlin metadata */
        @SerializedName(RegistrationStatParamsFactory.AUTH_APP_ID)
        @Nullable
        private final Integer authAppId;

        /* JADX WARN: Enum visitor error
        jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v34 com.vk.stat.sak.scheme.SchemeStatSak$TypeRegistrationItem$Error[], still in use, count: 1, list:
          (r0v34 com.vk.stat.sak.scheme.SchemeStatSak$TypeRegistrationItem$Error[]) from 0x01c6: INVOKE (r0v34 com.vk.stat.sak.scheme.SchemeStatSak$TypeRegistrationItem$Error[]) STATIC call: kotlin.enums.EnumEntriesKt.enumEntries(java.lang.Enum[]):kotlin.enums.EnumEntries A[MD:<E extends java.lang.Enum<E>>:(E extends java.lang.Enum<E>[]):kotlin.enums.EnumEntries<E extends java.lang.Enum<E>> (m), WRAPPED] (LINE:455)
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
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b#\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001j\u0002\b\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001dj\u0002\b\u001ej\u0002\b\u001fj\u0002\b j\u0002\b!j\u0002\b\"j\u0002\b#¨\u0006$"}, d2 = {"Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeRegistrationItem$Error;", "", "FLOOD", "ACCESS_ERROR", "AUTH_CODE_MISSING", "AUTH_UNKNOWN", AnalyticsErrorType.SERVER_ERROR, "SMS_RESEND_DELAY", "INVALID_PARAMS", "MISSING_PARAMS", "INVALID_CAPTCHA", "INVALID_CODE", "INVALID_NAME", "INVALID_SEX", "INVALID_BIRTHDAY", "INVALID_PASSWORD", "INVALID_PHONE", "INVALID_EMAIL", "PHONE_BANNED", "PHONE_HOLDER_BANNED", "PHONE_ALREADY_USED", "PHONE_CHANGE_LIMIT", "PHONE_CHECK_CODE_LIMIT", "EXTERNAL_INVALID_PHONE", "EXTERNAL_PHONE_PROCESSING", "EMAIL_ALREADY_USED", "MOBILE_QR_VIDEO_LOAD_ERROR", "MOBILE_QR_AUTH_ERROR", "ACCOUNTS_LIMIT_REACHED_ERROR", "VKME_ACCOUNTS_LIMIT_REACHED_ERROR", "USER_DELETED", "USER_LOGOUT_ALL", "EMPTY_LOGIN", "EMPTY_PASSWORD", "LOGIN_PHONE_CHANGE", "ACCOUNT_NOT_EXIST", "sak_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
        public static final class Error {
            FLOOD,
            ACCESS_ERROR,
            AUTH_CODE_MISSING,
            AUTH_UNKNOWN,
            SERVER_ERROR,
            SMS_RESEND_DELAY,
            INVALID_PARAMS,
            MISSING_PARAMS,
            INVALID_CAPTCHA,
            INVALID_CODE,
            INVALID_NAME,
            INVALID_SEX,
            INVALID_BIRTHDAY,
            INVALID_PASSWORD,
            INVALID_PHONE,
            INVALID_EMAIL,
            PHONE_BANNED,
            PHONE_HOLDER_BANNED,
            PHONE_ALREADY_USED,
            PHONE_CHANGE_LIMIT,
            PHONE_CHECK_CODE_LIMIT,
            EXTERNAL_INVALID_PHONE,
            EXTERNAL_PHONE_PROCESSING,
            EMAIL_ALREADY_USED,
            MOBILE_QR_VIDEO_LOAD_ERROR,
            MOBILE_QR_AUTH_ERROR,
            ACCOUNTS_LIMIT_REACHED_ERROR,
            VKME_ACCOUNTS_LIMIT_REACHED_ERROR,
            USER_DELETED,
            USER_LOGOUT_ALL,
            EMPTY_LOGIN,
            EMPTY_PASSWORD,
            LOGIN_PHONE_CHANGE,
            ACCOUNT_NOT_EXIST;

            private static final /* synthetic */ EnumEntries kastatsbilkvmocb;

            static {
                kastatsbilkvmocb = EnumEntriesKt.enumEntries(errorArr);
            }

            private Error() {
                super(str, i);
            }

            @NotNull
            public static EnumEntries<Error> getEntries() {
                return kastatsbilkvmocb;
            }

            public static Error valueOf(String str) {
                return (Error) Enum.valueOf(Error.class, str);
            }

            public static Error[] values() {
                return (Error[]) kastatsbilkvmoca.clone();
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000\r\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0003\b¡\u0003\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001j\u0002\b\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001dj\u0002\b\u001ej\u0002\b\u001fj\u0002\b j\u0002\b!j\u0002\b\"j\u0002\b#j\u0002\b$j\u0002\b%j\u0002\b&j\u0002\b'j\u0002\b(j\u0002\b)j\u0002\b*j\u0002\b+j\u0002\b,j\u0002\b-j\u0002\b.j\u0002\b/j\u0002\b0j\u0002\b1j\u0002\b2j\u0002\b3j\u0002\b4j\u0002\b5j\u0002\b6j\u0002\b7j\u0002\b8j\u0002\b9j\u0002\b:j\u0002\b;j\u0002\b<j\u0002\b=j\u0002\b>j\u0002\b?j\u0002\b@j\u0002\bAj\u0002\bBj\u0002\bCj\u0002\bDj\u0002\bEj\u0002\bFj\u0002\bGj\u0002\bHj\u0002\bIj\u0002\bJj\u0002\bKj\u0002\bLj\u0002\bMj\u0002\bNj\u0002\bOj\u0002\bPj\u0002\bQj\u0002\bRj\u0002\bSj\u0002\bTj\u0002\bUj\u0002\bVj\u0002\bWj\u0002\bXj\u0002\bYj\u0002\bZj\u0002\b[j\u0002\b\\j\u0002\b]j\u0002\b^j\u0002\b_j\u0002\b`j\u0002\baj\u0002\bbj\u0002\bcj\u0002\bdj\u0002\bej\u0002\bfj\u0002\bgj\u0002\bhj\u0002\bij\u0002\bjj\u0002\bkj\u0002\blj\u0002\bmj\u0002\bnj\u0002\boj\u0002\bpj\u0002\bqj\u0002\brj\u0002\bsj\u0002\btj\u0002\buj\u0002\bvj\u0002\bwj\u0002\bxj\u0002\byj\u0002\bzj\u0002\b{j\u0002\b|j\u0002\b}j\u0002\b~j\u0002\b\u007fj\u0003\b\u0080\u0001j\u0003\b\u0081\u0001j\u0003\b\u0082\u0001j\u0003\b\u0083\u0001j\u0003\b\u0084\u0001j\u0003\b\u0085\u0001j\u0003\b\u0086\u0001j\u0003\b\u0087\u0001j\u0003\b\u0088\u0001j\u0003\b\u0089\u0001j\u0003\b\u008a\u0001j\u0003\b\u008b\u0001j\u0003\b\u008c\u0001j\u0003\b\u008d\u0001j\u0003\b\u008e\u0001j\u0003\b\u008f\u0001j\u0003\b\u0090\u0001j\u0003\b\u0091\u0001j\u0003\b\u0092\u0001j\u0003\b\u0093\u0001j\u0003\b\u0094\u0001j\u0003\b\u0095\u0001j\u0003\b\u0096\u0001j\u0003\b\u0097\u0001j\u0003\b\u0098\u0001j\u0003\b\u0099\u0001j\u0003\b\u009a\u0001j\u0003\b\u009b\u0001j\u0003\b\u009c\u0001j\u0003\b\u009d\u0001j\u0003\b\u009e\u0001j\u0003\b\u009f\u0001j\u0003\b \u0001j\u0003\b¡\u0001j\u0003\b¢\u0001j\u0003\b£\u0001j\u0003\b¤\u0001j\u0003\b¥\u0001j\u0003\b¦\u0001j\u0003\b§\u0001j\u0003\b¨\u0001j\u0003\b©\u0001j\u0003\bª\u0001j\u0003\b«\u0001j\u0003\b¬\u0001j\u0003\b\u00ad\u0001j\u0003\b®\u0001j\u0003\b¯\u0001j\u0003\b°\u0001j\u0003\b±\u0001j\u0003\b²\u0001j\u0003\b³\u0001j\u0003\b´\u0001j\u0003\bµ\u0001j\u0003\b¶\u0001j\u0003\b·\u0001j\u0003\b¸\u0001j\u0003\b¹\u0001j\u0003\bº\u0001j\u0003\b»\u0001j\u0003\b¼\u0001j\u0003\b½\u0001j\u0003\b¾\u0001j\u0003\b¿\u0001j\u0003\bÀ\u0001j\u0003\bÁ\u0001j\u0003\bÂ\u0001j\u0003\bÃ\u0001j\u0003\bÄ\u0001j\u0003\bÅ\u0001j\u0003\bÆ\u0001j\u0003\bÇ\u0001j\u0003\bÈ\u0001j\u0003\bÉ\u0001j\u0003\bÊ\u0001j\u0003\bË\u0001j\u0003\bÌ\u0001j\u0003\bÍ\u0001j\u0003\bÎ\u0001j\u0003\bÏ\u0001j\u0003\bÐ\u0001j\u0003\bÑ\u0001j\u0003\bÒ\u0001j\u0003\bÓ\u0001j\u0003\bÔ\u0001j\u0003\bÕ\u0001j\u0003\bÖ\u0001j\u0003\b×\u0001j\u0003\bØ\u0001j\u0003\bÙ\u0001j\u0003\bÚ\u0001j\u0003\bÛ\u0001j\u0003\bÜ\u0001j\u0003\bÝ\u0001j\u0003\bÞ\u0001j\u0003\bß\u0001j\u0003\bà\u0001j\u0003\bá\u0001j\u0003\bâ\u0001j\u0003\bã\u0001j\u0003\bä\u0001j\u0003\bå\u0001j\u0003\bæ\u0001j\u0003\bç\u0001j\u0003\bè\u0001j\u0003\bé\u0001j\u0003\bê\u0001j\u0003\bë\u0001j\u0003\bì\u0001j\u0003\bí\u0001j\u0003\bî\u0001j\u0003\bï\u0001j\u0003\bð\u0001j\u0003\bñ\u0001j\u0003\bò\u0001j\u0003\bó\u0001j\u0003\bô\u0001j\u0003\bõ\u0001j\u0003\bö\u0001j\u0003\b÷\u0001j\u0003\bø\u0001j\u0003\bù\u0001j\u0003\bú\u0001j\u0003\bû\u0001j\u0003\bü\u0001j\u0003\bý\u0001j\u0003\bþ\u0001j\u0003\bÿ\u0001j\u0003\b\u0080\u0002j\u0003\b\u0081\u0002j\u0003\b\u0082\u0002j\u0003\b\u0083\u0002j\u0003\b\u0084\u0002j\u0003\b\u0085\u0002j\u0003\b\u0086\u0002j\u0003\b\u0087\u0002j\u0003\b\u0088\u0002j\u0003\b\u0089\u0002j\u0003\b\u008a\u0002j\u0003\b\u008b\u0002j\u0003\b\u008c\u0002j\u0003\b\u008d\u0002j\u0003\b\u008e\u0002j\u0003\b\u008f\u0002j\u0003\b\u0090\u0002j\u0003\b\u0091\u0002j\u0003\b\u0092\u0002j\u0003\b\u0093\u0002j\u0003\b\u0094\u0002j\u0003\b\u0095\u0002j\u0003\b\u0096\u0002j\u0003\b\u0097\u0002j\u0003\b\u0098\u0002j\u0003\b\u0099\u0002j\u0003\b\u009a\u0002j\u0003\b\u009b\u0002j\u0003\b\u009c\u0002j\u0003\b\u009d\u0002j\u0003\b\u009e\u0002j\u0003\b\u009f\u0002j\u0003\b \u0002j\u0003\b¡\u0002j\u0003\b¢\u0002j\u0003\b£\u0002j\u0003\b¤\u0002j\u0003\b¥\u0002j\u0003\b¦\u0002j\u0003\b§\u0002j\u0003\b¨\u0002j\u0003\b©\u0002j\u0003\bª\u0002j\u0003\b«\u0002j\u0003\b¬\u0002j\u0003\b\u00ad\u0002j\u0003\b®\u0002j\u0003\b¯\u0002j\u0003\b°\u0002j\u0003\b±\u0002j\u0003\b²\u0002j\u0003\b³\u0002j\u0003\b´\u0002j\u0003\bµ\u0002j\u0003\b¶\u0002j\u0003\b·\u0002j\u0003\b¸\u0002j\u0003\b¹\u0002j\u0003\bº\u0002j\u0003\b»\u0002j\u0003\b¼\u0002j\u0003\b½\u0002j\u0003\b¾\u0002j\u0003\b¿\u0002j\u0003\bÀ\u0002j\u0003\bÁ\u0002j\u0003\bÂ\u0002j\u0003\bÃ\u0002j\u0003\bÄ\u0002j\u0003\bÅ\u0002j\u0003\bÆ\u0002j\u0003\bÇ\u0002j\u0003\bÈ\u0002j\u0003\bÉ\u0002j\u0003\bÊ\u0002j\u0003\bË\u0002j\u0003\bÌ\u0002j\u0003\bÍ\u0002j\u0003\bÎ\u0002j\u0003\bÏ\u0002j\u0003\bÐ\u0002j\u0003\bÑ\u0002j\u0003\bÒ\u0002j\u0003\bÓ\u0002j\u0003\bÔ\u0002j\u0003\bÕ\u0002j\u0003\bÖ\u0002j\u0003\b×\u0002j\u0003\bØ\u0002j\u0003\bÙ\u0002j\u0003\bÚ\u0002j\u0003\bÛ\u0002j\u0003\bÜ\u0002j\u0003\bÝ\u0002j\u0003\bÞ\u0002j\u0003\bß\u0002j\u0003\bà\u0002j\u0003\bá\u0002j\u0003\bâ\u0002j\u0003\bã\u0002j\u0003\bä\u0002j\u0003\bå\u0002j\u0003\bæ\u0002j\u0003\bç\u0002j\u0003\bè\u0002j\u0003\bé\u0002j\u0003\bê\u0002j\u0003\bë\u0002j\u0003\bì\u0002j\u0003\bí\u0002j\u0003\bî\u0002j\u0003\bï\u0002j\u0003\bð\u0002j\u0003\bñ\u0002j\u0003\bò\u0002j\u0003\bó\u0002j\u0003\bô\u0002j\u0003\bõ\u0002j\u0003\bö\u0002j\u0003\b÷\u0002j\u0003\bø\u0002j\u0003\bù\u0002j\u0003\bú\u0002j\u0003\bû\u0002j\u0003\bü\u0002j\u0003\bý\u0002j\u0003\bþ\u0002j\u0003\bÿ\u0002j\u0003\b\u0080\u0003j\u0003\b\u0081\u0003j\u0003\b\u0082\u0003j\u0003\b\u0083\u0003j\u0003\b\u0084\u0003j\u0003\b\u0085\u0003j\u0003\b\u0086\u0003j\u0003\b\u0087\u0003j\u0003\b\u0088\u0003j\u0003\b\u0089\u0003j\u0003\b\u008a\u0003j\u0003\b\u008b\u0003j\u0003\b\u008c\u0003j\u0003\b\u008d\u0003j\u0003\b\u008e\u0003j\u0003\b\u008f\u0003j\u0003\b\u0090\u0003j\u0003\b\u0091\u0003j\u0003\b\u0092\u0003j\u0003\b\u0093\u0003j\u0003\b\u0094\u0003j\u0003\b\u0095\u0003j\u0003\b\u0096\u0003j\u0003\b\u0097\u0003j\u0003\b\u0098\u0003j\u0003\b\u0099\u0003j\u0003\b\u009a\u0003j\u0003\b\u009b\u0003j\u0003\b\u009c\u0003j\u0003\b\u009d\u0003j\u0003\b\u009e\u0003j\u0003\b\u009f\u0003j\u0003\b \u0003j\u0003\b¡\u0003¨\u0006¢\u0003"}, d2 = {"Lcom/vk/stat/sak/scheme/SchemeStatSak$TypeRegistrationItem$EventType;", "", "SCREEN_PROCEED", "SCREEN_RETURN", "SCREEN_SKIP", "SCREEN_BLUR", "SCREEN_FOCUS", "SCREEN_LOADING_ABORTED", "SCREEN_LOADING_FAILED", "SILENT_AUTH_INFO_OBTAIN_ERROR", "COMMON_SERVER_ERROR", "CONNECT_FACEBOOK_FAILED", "CONNECT_OK_FAILED", "CONNECT_TWITTER_FAILED", "CONNECT_GMAIL_FAILED", "SHOW_IMPORT_CONTACTS_CONFIRMATION_MODAL", "RESEND_SMS_CODE", "RESEND_SMS_CODE_FAILED", "SEND_SMS_CODE_FAILED", "SMS_CODE_DETECTED", "SEX_DETECTED", "INCORRECT_SMS_CODE", "INCORRECT_PASSWORD", "INCORRECT_NAME", "INCORRECT_CAPTCHA", "INCORRECT_PHONE_NUMBER", "INCORRECT_PASSWORD_POPUP", "INCORRECT_EMAIL", "INCORRECT_EMAIL_CODE", "SELECT_COUNTRY", "SELECT_COUNTRY_DONE", "INPUT_NUMBER_INTERACTION", "INPUT_CODE_INTERACTION", "INPUT_EMAIL_CODE_INTERACTION", "INPUT_EMAIL_INTERACTION", "PROCEED_OTHER_COUNTRY_CODE", "EXISTING_PHONE_NUMBER", "EXISTING_PHONE_NUMBER_TAP", "IMPORT_CONTACTS_FAILED", "PHOTO_UPLOADING_ABORTED", "PHOTO_UPLOADING_FAILED", "PUSH_REQUEST_ALLOW", "PUSH_REQUEST_DENY", "SELECT_SUBJECT", "SUBSCRIBE_COMMUNITY", "UNSUBSCRIBE_COMMUNITY", "SEE_MORE", "SILENT_TOKEN_PROVIDED", "SILENT_TOKEN_PROVIDED_AUTHORIZATION", "SILENT_TOKEN_PROVIDED_REGISTRATION", "AUTH_BY_LOGIN", "AUTH_SILENT", "AUTH_FAST_SILENT", "AUTH_BY_OAUTH", "REGISTRATION", "AUTH_BY_UNKNOWN", "AUTH_BY_PHONE", "AUTH_BY_BUTTON", "AUTH_BY_EMAIL", "AUTH_BY_ECOSYSTEM_PUSH", "AUTH_BY_AUTOLOGIN", "AUTH_BY_QR_CODE", "AUTH_BY_RESTORE", "AUTH_CONFIRM", "CHOOSE_ANOTHER_WAY", "ACCESS_TOKEN_PROVIDED", "OPEN_ACCOUNT", "AUTH_SUBAPP", "AUTH_SUBAPP_SUCCESS", "PROFILE_INFO_RETRIEVED", "CODE_SEND", "CODE_CALL", "SUCCESS_2FA", "PARTIAL_EXPAND_SUCCESS", "UNIFIED_ACCOUNT_ALL_SERVICES", "FAST_SILENT_TOKEN_PROVIDED_AUTHORIZATION", "SILENT_AUTH_RESUME_CLICK", "TO_VK_CLIENT_UNSAFE_ST", "FROM_VK_CLIENT_FULL_ST", "TO_VK_CLIENT_WITHOUT_ST", "FROM_VK_CLIENT_WITHOUT_ST", "LOADING_SILENT_AUTH_EXISTING_ACCOUNT", "SERVICE_OPEN_DL", "SERVICE_NOT_OPEN", "VK_MAIL_CREATED", "VK_MAIL_SELECTED", "ERROR_VK_MAIL_CREATED", "ERROR_VK_MAIL_LOGIN", "LOGIN_TAP", "PASSW_TAP", "EMAIL_REG_ALLOWED", "EMAIL_REG_DENIED", "REGISTRATION_EMAIL_NOT_FOUND", "REGISTRATION_PASSWORD_NOT_FOUND", "ERROR_NUMBER_LINKED", "ONE_TAP_START_BUTTON_SHOW", "ONE_TAP_USER_BUTTON_SHOW", "ONE_TAP_EMPTY_BUTTON_SHOW", "ONE_TAP_START_BUTTON_CLICK", "ONE_TAP_USER_BUTTON_CLICK", "ONE_TAP_EMPTY_BUTTON_CLICK", "FIRST_AUTHORIZATION", "REGISTRATION_START", "REGISTRATION_COMPLETE", "AUTH_START", "NO_USER_ACCOUNT_TAP", "INPUT_PHONE", "INPUT_EMAIL", "INPUT_LOGIN", "AVAILABLE_AUTH_WITHOUT_PASSWORD", "SELECT_AUTH_BY_PHONE", "SELECT_AUTH_BY_PASSWORD", "NO_WINDOW_OPENER_ERROR", "REGISTRATION_EXISTING_ACCOUNT_WITHOUT_PASSWORD", "AUTH_PASSWORD", "EXTERNAL_LINK_MINIAPP_OPEN", "EXTERNAL_LINK_MINIAPP_SUCCESS_RETURN", "INCORRECT_CALL_CODE", "CALL_CODE_SUCCESS_VERIFICATION", "INCORRECT_AUTHENTICATOR_CODE", "SUCCESS_2FA_AUTHENTICATOR_CODE", "TOKEN_RELOAD_FROM_AM", "CONTINUE_AS_USERNAME", "TYPE_2FA_ACTIVE", "QR_CODE_LINK_OPEN", "QR_CODE_EXPIRED", "ENTRY_LINK_OPEN", "ENTRY_BY_QR_CODE_CONFIRM_TAP", "CONTINUE_VERIFICATION_TAP", "VERIFY_BY_ANOTHER_WAY_TAP", "VERIFY_AGAIN_TAP", "PHONE_SUCCESS_VERIFICATION", "ALERT_VERIFICATION_CODE_ERROR", "ALERT_SMS_ALREADY_SEND", "ALERT_NO_AVAILABLE_FACTORS", "CAPTCHA_SUCCESS", "ENTRY_CONFIRM_TAP", "ALERT_UNSAFE_AUTH_ERROR", "ALERT_REFRESH_ERROR", "AUTH_SUBPROFILE", "SMART_LOCK_USE_SUGGEST", "SMART_LOCK_USE_AGREED", "SMART_LOCK_USE_CANCELED", "SMART_LOCK_SAVE_SUGGEST", "SMART_LOCK_SAVING_CONFIRMED", "SMART_LOCK_SAVING_DECLINED", "GOOGLE_PHONE_HINT_OPENED", "GOOGLE_PHONE_HINT_ADDED", "GOOGLE_PHONE_HINT_SKIP", "GOOGLE_PHONE_HINT_NOTHING_FOUND", "CREATE_SUBPROFILE_CLICK", "OAUTH_ASK_CONFIRMED", "YANDEX_NEW_NUMBER", "ALERT_SOMETHING_WENT_WRONG", "TINKOFF_NEW_NUMBER", "SBER_NEW_NUMBER", "MULTIACC_ADD_ANOTHER_ACCOUNT_TAP", "MULTIACC_DROP_ACCOUNT_TAP", "MULTIACC_DROP_ACCOUNT", "MULTI_ACC_ADD_ACCOUNT_TAP", "MULTI_ACC_ADD_ACCOUNT", "ACCOUNT_WAS_ADDED_TO_MULTIACC_WITH_AUTH", "SELECT_ACCOUNT_TAP", "SWITCH_ACCOUNT_TAP", "SWITCH_FROM_ACCOUNT", "SWITCH_TO_ACCOUNT", "DROP_ACCOUNT_TAP", "FULL_LOGOUT", "REAUTHTORIZATION_START", "REAUTHTORIZATION_CANCELLED", "AUTH_BY_PASSKEY", "AUTH_PASSKEY_ONLY_FOR_PHONE_NO_START", "PASSKEY_SCREEN_OPEN", "PASSKEY_SCREEN_CANCELED", "START_PASSKEY_AGAIN_TAP", "GOOGLE_NEW_NUMBER", "SERVICE_LOGOUT", "CONTINUE_TAP", "CHOOSE_ANOTHER_ACCOUNT_TAP", "CHOOSE_ACCOUNT_TAP", "ITS_OK_TAP", "CAPTCHA_REFRESH", "REFUSE_ONBOARDING_PASSKEY", "TRY_AGAIN", "CONTINUE_REG_ADD_TAP", "REGISTRATION_ADD", "ERROR_USER_IS_TOO_YOUNG", "CHOOSE_PASSKEY", "CHOOSE_ECOSYSTEM_PUSH", "CHOOSE_SMS", "CHOOSE_CALL_RESET", "CHOOSE_EMAIL", "CHOOSE_RESERVE_CODE", "CHOOSE_OFFICIAL_MESSENGER", "CHOOSE_MAX_MESSENGER", "CHOOSE_RESTORE", "CHOOSE_APP", "FACTOR_AVAILABLE", "AVAILABLE_FACTORS", "USER_NOT_FOUND", "CALLIN_CALL_TAP", "ALERT_CALLIN_ENTRY_ERROR", "CALLIN_NUMBERS_ARE_OVER", "CALLIN_PHONE_NUMBER_CHANGED", "ERROR_INVALID_REQUEST", GmsRpc.ERROR_INTERNAL_SERVER_ERROR, "UNAVAILABLE_AUTH_BY_AUTOLOGIN", "CALLIN_LIBVERIFY_STARTED", "CREATE_BUSINESS_START", "OAUTH_HIDDEN", "ALFA_NEW_NUMBER", "USED_EXISTING_EMAIL", "EMAIL_SKIP_TAP", "EMAIL_DID_NOT_SEND_ALERT", "SEND_AGAIN_TAP", "BIRTHDAY_TOOLTIP_TAP", "EMAIL_SUCCESS_VERIFICATION", "CLOSE_ALERT", "REGISTRATION_PERMISSION_SKIP_BUTTON_TAP", "EXISTING_PHONE_NUMBER_BUTTON_TAP", "REGISTRATION_PERMISSION_BUTTON_TAP", "INCORRECT_BDAY", "BACKUP_RESTORED", "FEED_OPENED", "WEB_REGISTRATION", "MINI_APP_VK_CONNECT_LAUNCH_SCREEN_ENTER", "MINI_APP_VK_CONNECT_LAUNCH_SCREEN_PERMISSIONS_ACCEPTED", "MINI_APP_VK_CONNECT_LAUNCH_SCREEN_VIEW_PERMISSIONS", "MINI_APP_VK_CONNECT_LAUNCH_SCREEN_VIEW_CONNECT_POLICY", "MINI_APP_VK_CONNECT_LAUNCH_SCREEN_VIEW_CONNECT_TERMS", "MINI_APP_VK_CONNECT_LAUNCH_SCREEN_VIEW_SERVICE_POLICY", "MINI_APP_VK_CONNECT_LAUNCH_SCREEN_VIEW_SERVICE_TERMS", "MAIL_LINKED_ANOTHER_ACCOUNT_LOGIN", "OK_NEW_NUMBER", "REGISTRATION_PHONE_CONTINUE_TAP", "CHANGE_NUMBER_TO_VERIFY", "MOBILE_QR_QR_CODE_BUTTON_SHOW", "MOBILE_QR_QR_CODE_BUTTON_TAP", "MOBILE_QR_VIDEO_LOAD_SUCCESS", "MOBILE_QR_SCAN_QR_CODE_TAP", "MOBILE_QR_CLOSE_GUIDE_TAP", "MOBILE_QR_VIDEO_LOADING_FAILED", "MOBILE_QR_TRY_AGAIN_TAP", "MOBILE_QR_VIDEO_LOADING", "MOBILE_QR_CLOSE_ALERT_TAP", "MOBILE_QR_AUTH_WITH_QR_TAP", "MOBILE_QR_MORE_INFO_TAP", "CHOOSE_ANOTHER_ACCOUNT", "ITS_NOT_MY_ACCOUNT", "FORGOT_PASSWORD", "ACCOUNTS_LIMIT_REACHED_ERROR", "MOBILE_QR_INCORRECT_QR_SCANNED", "TRY_VERIFY_AGAIN", "BACK_TO_REGISTRATION_START", "BLOCKSTORE_RELOAD", "INPUT_BIRTHDAY", "BIRTHDAY_CALENDAR_ICON_TAP", "PINCODE_INPUT_INTERACTION", "PINCODE_SUCCESS_VALIDATION", "PINCODE_INCORRECT", "PINCODE_TOO_MANY_ATTEMPTS_ALERT", "PINCODE_RESET_TAP", "OAUTH_BUTTON_SHOW", "ACCOUNT_MANAGER_RELOAD", "VKME_DROP_ACCOUNT_TAP", "VKME_ADD_ANOTHER_ACCOUNT_TAP", "MAX_ACCOUNT_ALERT", "AUTH_QR_CODE_START", "AUTH_CANCEL_TAP", "INCORRECT_OTP_CODE", "PHONE_REUSE_REQUESTED", "PASSWORD_AUTOFILL", "INPUT_PASSWORD_INTERACTION", "CLOSE_ALERT_TAP", "EMAIL_FORWARDING_ERROR", "EMAIL_FORWARDING_SUCCESS", "OTP_MESSENGER_LIBVERIFY_STARTED", "ONEPASS_CONNECT_AGREE_TAP", "ONEPASS_CONNECT_CANCEL_TAP", "CHOOSE_ENTER_BY_MAIL_PASS", "ALERT_DELETE_PROFILE", "ALERT_SILENT_AUTH_ADD_INFO", "DROP_ACCOUNT_FROM_SAVED", "AUTH_SAVED_START", "ADD_ACCOUNT_BUTTON_TAP", "ADD_ACCOUNT_TO_SAVED_START", "OK_HEADS_LOGIN_ERROR", "OK_AUTH_ERROR", "OK_REGISTRATION_ERROR", "ALERT_DELETE_PROFILE_CANCELED", "ALERT_SILENT_AUTH_ADD_INFO_CANCELED", "ALERT_SILENT_AUTH_ADD_INFO_ACCEPT", "ALERT_QR_CODE_OPEN_SCANNER", "SCANNER_TAB", "CAMERA_SOURCE_FOR_QR_CODE", "CREATE_RELATED_TAP", "QR_CODE_SHOW", "LOGIN_LATER_TAP", "AUTH_BY_QR_APP", "QR_CODE_REFRESH_TAP", "BIOMETRICS_VALIDATION_REFUSED", "INPUT_LOGIN_INTERACTION", "OAUTH_BUTTON_TAP", "MORE_INFO_BUTTON_TAP", "ALERT_WRONG_INPUT", "RESTORE_AUTH_BUTTON_TAP", "RESTORE_AUTH_BUTTON_TAP_CANCEL", "ALERT_REGISTRATION_CONTINUE", "ALERT_REGISTRATION_CONTINUE_EXIT_BUTTON_TAP", "RESTORE_AUTH_START", "MAIL_RESTORE_START", "REDIRECT_MAIL_RESTORE", "EMAIL_ERROR", "ERROR_EMAIL_ALREADY_LINKED", "EMAIL_NEED_PASSWORD_CHANGE", "NO_ACCESS_TO_PHONE_TAP", "GO_TO_MAX_TAP", "MAX_CONNECT_AGREE_TAP", "MAX_CONNECT_CANCEL_TAP", "MAX_APP_OPENED", "APP_MARKET_OPENED", "MAX_APP_OPEN_ATTEMPT", "OPEN_MARKET_FAILED", "AUTH_BY_MAX", "AUTH_BY_MAX_CODE", "BIND_ACCOUNTS_TAP", "OAUTH_TO_GRAY_VKID_BIND_CLOSE_TAP", "SUCCESS_BIND_TO_VKID", "NO_BIND_OAUTH_NEEDED_TAP", "GRAY_VKID_TO_OAUTH_BIND_CLOSE_TAP", "SUCCESS_BIND_GRAY_VKID", "YES_MY_ACCOUNT_TAP", "ALERT_VERIFICATION_BY_MAX_MESSENGER_TIMEOUT", "ALERT_VERIFICATION_BY_MAX_MESSENGER_CANCEL", "RUSTORE_SEAMLESS_INSTALL_AVAILABLE", "RUSTORE_SEAMLESS_INSTALL_STARTED", "RUSTORE_SEAMLESS_INSTALL_SUCCESS", "RUSTORE_SEAMLESS_INSTALL_MAX_MESSENGER_NOT_DOWNLOADED_ERROR", "RUSTORE_SEAMLESS_INSTALL_DEFAULT_CLIENT_NOT_DOWNLOADED_ERROR", "RUSTORE_SEAMLESS_INSTALL_CANCEL", "RUSTORE_SEAMLESS_INSTALL_UNKNOWN_ERROR", "RETURN_FROM_MAX_APP", "DOUBTFUL_AUTH_CHECK", "PASSWORD_AUTOFILL_CHOOSE_ACCOUNT_TAP", "PASSWORD_AUTOFILL_CLOSE_TAP", "AUTH_BY_VKME", "VERIFICATION_BY_MAX_MESSENGER_CANCEL", "RUSTORE_SEAMLESS_INSTALL_SERVICE_CONNECTION", "RUSTORE_SEAMLESS_INSTALL_SERVICE_CONNECTION_ERROR", "RUSTORE_SEAMLESS_INSTALL_ERROR", "AGREE_ACTUAL_PHONE_TAP", "CHANGE_ACTUAL_PHONE_TAP", "CONFIRM_ACTUAL_PHONE_BY_USER_CLOSE_TAP", "AGREE_ACTUAL_EMAIL_TAP", "CHANGE_ACTUAL_EMAIL_TAP", "CONFIRM_ACTUAL_EMAIL_CLOSE_TAP", "ACTUAL_EMAIL_CONFIRMATION_ERROR", "ACTUAL_EMAIL_CONFIRMATION_SUCCESS", "ADD_EMAIL_TAP", "ADD_EMAIL_BY_USER_CLOSE_TAP", "MAIL_LOGIN_ERROR", "CHOOSE_REGISTRATION_TAP", "REGISTRATION_MAIL_START", "CHOOSE_MY_ACCOUNT_TAP", "EMAIL_BLOCKED_ERROR", "EMAIL_2FA_ERROR", "START_AUTH_BY_GRAY_LINK", "AUTH_BY_MAIL", "AUTH_BY_GRAY_LINK", "AUTH_BY_GRAY_LINK_FAIL", "ENTER_PASSWORD_MAIL_LIMIT_ERROR", "DOMAIN_SELECTION_TAP", "CHOOSE_DOMAIN_TAP", "CREATE_EMAIL", "CREATE_CHILD_EMAIL", "MIGRATE_FROM_GMAIL", "REDIRECT_TO_MAIL_AUTH", "VERIFICATION_BY_MAX_MESSENGER_TIMEOUT", "KEYCHAIN_TOKEN_BACKUP_SUCCESS", "KEYCHAIN_TOKEN_RESTORE_SUCCESS", "MAIL_WHITE_PROMO_VKID_SDK_START", "SUCCESS_AUTH_MAIL_WHITE_PROMO", "AUTH_BY_WHITE_LINK", "AUTH_BY_VKID", "SEND_SMS_TAP", "MESSAGE_APP_OPEN", "MESSAGE_APP_OPEN_ERROR", "SMS_INBOX_FATAL_ERROR", "SMS_INBOX_TIMEOUT_ERROR", "SMS_INBOX_SEND_SMS_ERROR", "SMS_INBOX_INCORRECT_PHONE_ERROR", "SMS_INBOX_INCORRECT_TEXT_ERROR", "GO_TO_MAX_CHAT_TAP", "CHOOSE_MAX_CODE", "COUNTRY_MENU_SHOW", "SEARCH_COUNTRY_TAP", "SEARCH_COUNTRY_NOT_FOUND", "SEARCH_COUNTRY_INTERACTION", "OTP_MESSENGER_LIBVERIFY_SENDING", "MESSENGER_SIGN_UP_PROMO", "MESSENGER_APP_OPENED", "MESSENGER_STORE_OPENED", "QR_CODE_DEVICE_INFO_TAP", "QR_CODE_CLOSE_TAP", "QR_CODE_CANCEL_TAP", "INCORRECT_DEVICE_CODE", "DEVICE_CODE_SUCCESS", "ENTRY_BY_DEVICE_CODE_CONFIRM_TAP", "ENTRY_BY_DEVICE_CODE_CLOSER_TAP", "ALERT_DEVICE_CODE_CONNECTION_ERROR", "SMS_LIBVERIFY_STARTED", "CALL_LIBVERIFY_STARTED", "MOBILEID_LIBVERIFY_STARTED", "PUSH_LIBVERIFY_STARTED", "ALREADY_VERIFIED_LIBVERIFY_STARTED", "ALERT_VK_CLIENT_AUTH_FAILED", "AUTH_BY_VK_CLIENT", "VK_CLIENT_NOT_FOUND", "sak_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
        public enum EventType {
            SCREEN_PROCEED,
            SCREEN_RETURN,
            SCREEN_SKIP,
            SCREEN_BLUR,
            SCREEN_FOCUS,
            SCREEN_LOADING_ABORTED,
            SCREEN_LOADING_FAILED,
            SILENT_AUTH_INFO_OBTAIN_ERROR,
            COMMON_SERVER_ERROR,
            CONNECT_FACEBOOK_FAILED,
            CONNECT_OK_FAILED,
            CONNECT_TWITTER_FAILED,
            CONNECT_GMAIL_FAILED,
            SHOW_IMPORT_CONTACTS_CONFIRMATION_MODAL,
            RESEND_SMS_CODE,
            RESEND_SMS_CODE_FAILED,
            SEND_SMS_CODE_FAILED,
            SMS_CODE_DETECTED,
            SEX_DETECTED,
            INCORRECT_SMS_CODE,
            INCORRECT_PASSWORD,
            INCORRECT_NAME,
            INCORRECT_CAPTCHA,
            INCORRECT_PHONE_NUMBER,
            INCORRECT_PASSWORD_POPUP,
            INCORRECT_EMAIL,
            INCORRECT_EMAIL_CODE,
            SELECT_COUNTRY,
            SELECT_COUNTRY_DONE,
            INPUT_NUMBER_INTERACTION,
            INPUT_CODE_INTERACTION,
            INPUT_EMAIL_CODE_INTERACTION,
            INPUT_EMAIL_INTERACTION,
            PROCEED_OTHER_COUNTRY_CODE,
            EXISTING_PHONE_NUMBER,
            EXISTING_PHONE_NUMBER_TAP,
            IMPORT_CONTACTS_FAILED,
            PHOTO_UPLOADING_ABORTED,
            PHOTO_UPLOADING_FAILED,
            PUSH_REQUEST_ALLOW,
            PUSH_REQUEST_DENY,
            SELECT_SUBJECT,
            SUBSCRIBE_COMMUNITY,
            UNSUBSCRIBE_COMMUNITY,
            SEE_MORE,
            SILENT_TOKEN_PROVIDED,
            SILENT_TOKEN_PROVIDED_AUTHORIZATION,
            SILENT_TOKEN_PROVIDED_REGISTRATION,
            AUTH_BY_LOGIN,
            AUTH_SILENT,
            AUTH_FAST_SILENT,
            AUTH_BY_OAUTH,
            REGISTRATION,
            AUTH_BY_UNKNOWN,
            AUTH_BY_PHONE,
            AUTH_BY_BUTTON,
            AUTH_BY_EMAIL,
            AUTH_BY_ECOSYSTEM_PUSH,
            AUTH_BY_AUTOLOGIN,
            AUTH_BY_QR_CODE,
            AUTH_BY_RESTORE,
            AUTH_CONFIRM,
            CHOOSE_ANOTHER_WAY,
            ACCESS_TOKEN_PROVIDED,
            OPEN_ACCOUNT,
            AUTH_SUBAPP,
            AUTH_SUBAPP_SUCCESS,
            PROFILE_INFO_RETRIEVED,
            CODE_SEND,
            CODE_CALL,
            SUCCESS_2FA,
            PARTIAL_EXPAND_SUCCESS,
            UNIFIED_ACCOUNT_ALL_SERVICES,
            FAST_SILENT_TOKEN_PROVIDED_AUTHORIZATION,
            SILENT_AUTH_RESUME_CLICK,
            TO_VK_CLIENT_UNSAFE_ST,
            FROM_VK_CLIENT_FULL_ST,
            TO_VK_CLIENT_WITHOUT_ST,
            FROM_VK_CLIENT_WITHOUT_ST,
            LOADING_SILENT_AUTH_EXISTING_ACCOUNT,
            SERVICE_OPEN_DL,
            SERVICE_NOT_OPEN,
            VK_MAIL_CREATED,
            VK_MAIL_SELECTED,
            ERROR_VK_MAIL_CREATED,
            ERROR_VK_MAIL_LOGIN,
            LOGIN_TAP,
            PASSW_TAP,
            EMAIL_REG_ALLOWED,
            EMAIL_REG_DENIED,
            REGISTRATION_EMAIL_NOT_FOUND,
            REGISTRATION_PASSWORD_NOT_FOUND,
            ERROR_NUMBER_LINKED,
            ONE_TAP_START_BUTTON_SHOW,
            ONE_TAP_USER_BUTTON_SHOW,
            ONE_TAP_EMPTY_BUTTON_SHOW,
            ONE_TAP_START_BUTTON_CLICK,
            ONE_TAP_USER_BUTTON_CLICK,
            ONE_TAP_EMPTY_BUTTON_CLICK,
            FIRST_AUTHORIZATION,
            REGISTRATION_START,
            REGISTRATION_COMPLETE,
            AUTH_START,
            NO_USER_ACCOUNT_TAP,
            INPUT_PHONE,
            INPUT_EMAIL,
            INPUT_LOGIN,
            AVAILABLE_AUTH_WITHOUT_PASSWORD,
            SELECT_AUTH_BY_PHONE,
            SELECT_AUTH_BY_PASSWORD,
            NO_WINDOW_OPENER_ERROR,
            REGISTRATION_EXISTING_ACCOUNT_WITHOUT_PASSWORD,
            AUTH_PASSWORD,
            EXTERNAL_LINK_MINIAPP_OPEN,
            EXTERNAL_LINK_MINIAPP_SUCCESS_RETURN,
            INCORRECT_CALL_CODE,
            CALL_CODE_SUCCESS_VERIFICATION,
            INCORRECT_AUTHENTICATOR_CODE,
            SUCCESS_2FA_AUTHENTICATOR_CODE,
            TOKEN_RELOAD_FROM_AM,
            CONTINUE_AS_USERNAME,
            TYPE_2FA_ACTIVE,
            QR_CODE_LINK_OPEN,
            QR_CODE_EXPIRED,
            ENTRY_LINK_OPEN,
            ENTRY_BY_QR_CODE_CONFIRM_TAP,
            CONTINUE_VERIFICATION_TAP,
            VERIFY_BY_ANOTHER_WAY_TAP,
            VERIFY_AGAIN_TAP,
            PHONE_SUCCESS_VERIFICATION,
            ALERT_VERIFICATION_CODE_ERROR,
            ALERT_SMS_ALREADY_SEND,
            ALERT_NO_AVAILABLE_FACTORS,
            CAPTCHA_SUCCESS,
            ENTRY_CONFIRM_TAP,
            ALERT_UNSAFE_AUTH_ERROR,
            ALERT_REFRESH_ERROR,
            AUTH_SUBPROFILE,
            SMART_LOCK_USE_SUGGEST,
            SMART_LOCK_USE_AGREED,
            SMART_LOCK_USE_CANCELED,
            SMART_LOCK_SAVE_SUGGEST,
            SMART_LOCK_SAVING_CONFIRMED,
            SMART_LOCK_SAVING_DECLINED,
            GOOGLE_PHONE_HINT_OPENED,
            GOOGLE_PHONE_HINT_ADDED,
            GOOGLE_PHONE_HINT_SKIP,
            GOOGLE_PHONE_HINT_NOTHING_FOUND,
            CREATE_SUBPROFILE_CLICK,
            OAUTH_ASK_CONFIRMED,
            YANDEX_NEW_NUMBER,
            ALERT_SOMETHING_WENT_WRONG,
            TINKOFF_NEW_NUMBER,
            SBER_NEW_NUMBER,
            MULTIACC_ADD_ANOTHER_ACCOUNT_TAP,
            MULTIACC_DROP_ACCOUNT_TAP,
            MULTIACC_DROP_ACCOUNT,
            MULTI_ACC_ADD_ACCOUNT_TAP,
            MULTI_ACC_ADD_ACCOUNT,
            ACCOUNT_WAS_ADDED_TO_MULTIACC_WITH_AUTH,
            SELECT_ACCOUNT_TAP,
            SWITCH_ACCOUNT_TAP,
            SWITCH_FROM_ACCOUNT,
            SWITCH_TO_ACCOUNT,
            DROP_ACCOUNT_TAP,
            FULL_LOGOUT,
            REAUTHTORIZATION_START,
            REAUTHTORIZATION_CANCELLED,
            AUTH_BY_PASSKEY,
            AUTH_PASSKEY_ONLY_FOR_PHONE_NO_START,
            PASSKEY_SCREEN_OPEN,
            PASSKEY_SCREEN_CANCELED,
            START_PASSKEY_AGAIN_TAP,
            GOOGLE_NEW_NUMBER,
            SERVICE_LOGOUT,
            CONTINUE_TAP,
            CHOOSE_ANOTHER_ACCOUNT_TAP,
            CHOOSE_ACCOUNT_TAP,
            ITS_OK_TAP,
            CAPTCHA_REFRESH,
            REFUSE_ONBOARDING_PASSKEY,
            TRY_AGAIN,
            CONTINUE_REG_ADD_TAP,
            REGISTRATION_ADD,
            ERROR_USER_IS_TOO_YOUNG,
            CHOOSE_PASSKEY,
            CHOOSE_ECOSYSTEM_PUSH,
            CHOOSE_SMS,
            CHOOSE_CALL_RESET,
            CHOOSE_EMAIL,
            CHOOSE_RESERVE_CODE,
            CHOOSE_OFFICIAL_MESSENGER,
            CHOOSE_MAX_MESSENGER,
            CHOOSE_RESTORE,
            CHOOSE_APP,
            FACTOR_AVAILABLE,
            AVAILABLE_FACTORS,
            USER_NOT_FOUND,
            CALLIN_CALL_TAP,
            ALERT_CALLIN_ENTRY_ERROR,
            CALLIN_NUMBERS_ARE_OVER,
            CALLIN_PHONE_NUMBER_CHANGED,
            ERROR_INVALID_REQUEST,
            INTERNAL_SERVER_ERROR,
            UNAVAILABLE_AUTH_BY_AUTOLOGIN,
            CALLIN_LIBVERIFY_STARTED,
            CREATE_BUSINESS_START,
            OAUTH_HIDDEN,
            ALFA_NEW_NUMBER,
            USED_EXISTING_EMAIL,
            EMAIL_SKIP_TAP,
            EMAIL_DID_NOT_SEND_ALERT,
            SEND_AGAIN_TAP,
            BIRTHDAY_TOOLTIP_TAP,
            EMAIL_SUCCESS_VERIFICATION,
            CLOSE_ALERT,
            REGISTRATION_PERMISSION_SKIP_BUTTON_TAP,
            EXISTING_PHONE_NUMBER_BUTTON_TAP,
            REGISTRATION_PERMISSION_BUTTON_TAP,
            INCORRECT_BDAY,
            BACKUP_RESTORED,
            FEED_OPENED,
            WEB_REGISTRATION,
            MINI_APP_VK_CONNECT_LAUNCH_SCREEN_ENTER,
            MINI_APP_VK_CONNECT_LAUNCH_SCREEN_PERMISSIONS_ACCEPTED,
            MINI_APP_VK_CONNECT_LAUNCH_SCREEN_VIEW_PERMISSIONS,
            MINI_APP_VK_CONNECT_LAUNCH_SCREEN_VIEW_CONNECT_POLICY,
            MINI_APP_VK_CONNECT_LAUNCH_SCREEN_VIEW_CONNECT_TERMS,
            MINI_APP_VK_CONNECT_LAUNCH_SCREEN_VIEW_SERVICE_POLICY,
            MINI_APP_VK_CONNECT_LAUNCH_SCREEN_VIEW_SERVICE_TERMS,
            MAIL_LINKED_ANOTHER_ACCOUNT_LOGIN,
            OK_NEW_NUMBER,
            REGISTRATION_PHONE_CONTINUE_TAP,
            CHANGE_NUMBER_TO_VERIFY,
            MOBILE_QR_QR_CODE_BUTTON_SHOW,
            MOBILE_QR_QR_CODE_BUTTON_TAP,
            MOBILE_QR_VIDEO_LOAD_SUCCESS,
            MOBILE_QR_SCAN_QR_CODE_TAP,
            MOBILE_QR_CLOSE_GUIDE_TAP,
            MOBILE_QR_VIDEO_LOADING_FAILED,
            MOBILE_QR_TRY_AGAIN_TAP,
            MOBILE_QR_VIDEO_LOADING,
            MOBILE_QR_CLOSE_ALERT_TAP,
            MOBILE_QR_AUTH_WITH_QR_TAP,
            MOBILE_QR_MORE_INFO_TAP,
            CHOOSE_ANOTHER_ACCOUNT,
            ITS_NOT_MY_ACCOUNT,
            FORGOT_PASSWORD,
            ACCOUNTS_LIMIT_REACHED_ERROR,
            MOBILE_QR_INCORRECT_QR_SCANNED,
            TRY_VERIFY_AGAIN,
            BACK_TO_REGISTRATION_START,
            BLOCKSTORE_RELOAD,
            INPUT_BIRTHDAY,
            BIRTHDAY_CALENDAR_ICON_TAP,
            PINCODE_INPUT_INTERACTION,
            PINCODE_SUCCESS_VALIDATION,
            PINCODE_INCORRECT,
            PINCODE_TOO_MANY_ATTEMPTS_ALERT,
            PINCODE_RESET_TAP,
            OAUTH_BUTTON_SHOW,
            ACCOUNT_MANAGER_RELOAD,
            VKME_DROP_ACCOUNT_TAP,
            VKME_ADD_ANOTHER_ACCOUNT_TAP,
            MAX_ACCOUNT_ALERT,
            AUTH_QR_CODE_START,
            AUTH_CANCEL_TAP,
            INCORRECT_OTP_CODE,
            PHONE_REUSE_REQUESTED,
            PASSWORD_AUTOFILL,
            INPUT_PASSWORD_INTERACTION,
            CLOSE_ALERT_TAP,
            EMAIL_FORWARDING_ERROR,
            EMAIL_FORWARDING_SUCCESS,
            OTP_MESSENGER_LIBVERIFY_STARTED,
            ONEPASS_CONNECT_AGREE_TAP,
            ONEPASS_CONNECT_CANCEL_TAP,
            CHOOSE_ENTER_BY_MAIL_PASS,
            ALERT_DELETE_PROFILE,
            ALERT_SILENT_AUTH_ADD_INFO,
            DROP_ACCOUNT_FROM_SAVED,
            AUTH_SAVED_START,
            ADD_ACCOUNT_BUTTON_TAP,
            ADD_ACCOUNT_TO_SAVED_START,
            OK_HEADS_LOGIN_ERROR,
            OK_AUTH_ERROR,
            OK_REGISTRATION_ERROR,
            ALERT_DELETE_PROFILE_CANCELED,
            ALERT_SILENT_AUTH_ADD_INFO_CANCELED,
            ALERT_SILENT_AUTH_ADD_INFO_ACCEPT,
            ALERT_QR_CODE_OPEN_SCANNER,
            SCANNER_TAB,
            CAMERA_SOURCE_FOR_QR_CODE,
            CREATE_RELATED_TAP,
            QR_CODE_SHOW,
            LOGIN_LATER_TAP,
            AUTH_BY_QR_APP,
            QR_CODE_REFRESH_TAP,
            BIOMETRICS_VALIDATION_REFUSED,
            INPUT_LOGIN_INTERACTION,
            OAUTH_BUTTON_TAP,
            MORE_INFO_BUTTON_TAP,
            ALERT_WRONG_INPUT,
            RESTORE_AUTH_BUTTON_TAP,
            RESTORE_AUTH_BUTTON_TAP_CANCEL,
            ALERT_REGISTRATION_CONTINUE,
            ALERT_REGISTRATION_CONTINUE_EXIT_BUTTON_TAP,
            RESTORE_AUTH_START,
            MAIL_RESTORE_START,
            REDIRECT_MAIL_RESTORE,
            EMAIL_ERROR,
            ERROR_EMAIL_ALREADY_LINKED,
            EMAIL_NEED_PASSWORD_CHANGE,
            NO_ACCESS_TO_PHONE_TAP,
            GO_TO_MAX_TAP,
            MAX_CONNECT_AGREE_TAP,
            MAX_CONNECT_CANCEL_TAP,
            MAX_APP_OPENED,
            APP_MARKET_OPENED,
            MAX_APP_OPEN_ATTEMPT,
            OPEN_MARKET_FAILED,
            AUTH_BY_MAX,
            AUTH_BY_MAX_CODE,
            BIND_ACCOUNTS_TAP,
            OAUTH_TO_GRAY_VKID_BIND_CLOSE_TAP,
            SUCCESS_BIND_TO_VKID,
            NO_BIND_OAUTH_NEEDED_TAP,
            GRAY_VKID_TO_OAUTH_BIND_CLOSE_TAP,
            SUCCESS_BIND_GRAY_VKID,
            YES_MY_ACCOUNT_TAP,
            ALERT_VERIFICATION_BY_MAX_MESSENGER_TIMEOUT,
            ALERT_VERIFICATION_BY_MAX_MESSENGER_CANCEL,
            RUSTORE_SEAMLESS_INSTALL_AVAILABLE,
            RUSTORE_SEAMLESS_INSTALL_STARTED,
            RUSTORE_SEAMLESS_INSTALL_SUCCESS,
            RUSTORE_SEAMLESS_INSTALL_MAX_MESSENGER_NOT_DOWNLOADED_ERROR,
            RUSTORE_SEAMLESS_INSTALL_DEFAULT_CLIENT_NOT_DOWNLOADED_ERROR,
            RUSTORE_SEAMLESS_INSTALL_CANCEL,
            RUSTORE_SEAMLESS_INSTALL_UNKNOWN_ERROR,
            RETURN_FROM_MAX_APP,
            DOUBTFUL_AUTH_CHECK,
            PASSWORD_AUTOFILL_CHOOSE_ACCOUNT_TAP,
            PASSWORD_AUTOFILL_CLOSE_TAP,
            AUTH_BY_VKME,
            VERIFICATION_BY_MAX_MESSENGER_CANCEL,
            RUSTORE_SEAMLESS_INSTALL_SERVICE_CONNECTION,
            RUSTORE_SEAMLESS_INSTALL_SERVICE_CONNECTION_ERROR,
            RUSTORE_SEAMLESS_INSTALL_ERROR,
            AGREE_ACTUAL_PHONE_TAP,
            CHANGE_ACTUAL_PHONE_TAP,
            CONFIRM_ACTUAL_PHONE_BY_USER_CLOSE_TAP,
            AGREE_ACTUAL_EMAIL_TAP,
            CHANGE_ACTUAL_EMAIL_TAP,
            CONFIRM_ACTUAL_EMAIL_CLOSE_TAP,
            ACTUAL_EMAIL_CONFIRMATION_ERROR,
            ACTUAL_EMAIL_CONFIRMATION_SUCCESS,
            ADD_EMAIL_TAP,
            ADD_EMAIL_BY_USER_CLOSE_TAP,
            MAIL_LOGIN_ERROR,
            CHOOSE_REGISTRATION_TAP,
            REGISTRATION_MAIL_START,
            CHOOSE_MY_ACCOUNT_TAP,
            EMAIL_BLOCKED_ERROR,
            EMAIL_2FA_ERROR,
            START_AUTH_BY_GRAY_LINK,
            AUTH_BY_MAIL,
            AUTH_BY_GRAY_LINK,
            AUTH_BY_GRAY_LINK_FAIL,
            ENTER_PASSWORD_MAIL_LIMIT_ERROR,
            DOMAIN_SELECTION_TAP,
            CHOOSE_DOMAIN_TAP,
            CREATE_EMAIL,
            CREATE_CHILD_EMAIL,
            MIGRATE_FROM_GMAIL,
            REDIRECT_TO_MAIL_AUTH,
            VERIFICATION_BY_MAX_MESSENGER_TIMEOUT,
            KEYCHAIN_TOKEN_BACKUP_SUCCESS,
            KEYCHAIN_TOKEN_RESTORE_SUCCESS,
            MAIL_WHITE_PROMO_VKID_SDK_START,
            SUCCESS_AUTH_MAIL_WHITE_PROMO,
            AUTH_BY_WHITE_LINK,
            AUTH_BY_VKID,
            SEND_SMS_TAP,
            MESSAGE_APP_OPEN,
            MESSAGE_APP_OPEN_ERROR,
            SMS_INBOX_FATAL_ERROR,
            SMS_INBOX_TIMEOUT_ERROR,
            SMS_INBOX_SEND_SMS_ERROR,
            SMS_INBOX_INCORRECT_PHONE_ERROR,
            SMS_INBOX_INCORRECT_TEXT_ERROR,
            GO_TO_MAX_CHAT_TAP,
            CHOOSE_MAX_CODE,
            COUNTRY_MENU_SHOW,
            SEARCH_COUNTRY_TAP,
            SEARCH_COUNTRY_NOT_FOUND,
            SEARCH_COUNTRY_INTERACTION,
            OTP_MESSENGER_LIBVERIFY_SENDING,
            MESSENGER_SIGN_UP_PROMO,
            MESSENGER_APP_OPENED,
            MESSENGER_STORE_OPENED,
            QR_CODE_DEVICE_INFO_TAP,
            QR_CODE_CLOSE_TAP,
            QR_CODE_CANCEL_TAP,
            INCORRECT_DEVICE_CODE,
            DEVICE_CODE_SUCCESS,
            ENTRY_BY_DEVICE_CODE_CONFIRM_TAP,
            ENTRY_BY_DEVICE_CODE_CLOSER_TAP,
            ALERT_DEVICE_CODE_CONNECTION_ERROR,
            SMS_LIBVERIFY_STARTED,
            CALL_LIBVERIFY_STARTED,
            MOBILEID_LIBVERIFY_STARTED,
            PUSH_LIBVERIFY_STARTED,
            ALREADY_VERIFIED_LIBVERIFY_STARTED,
            ALERT_VK_CLIENT_AUTH_FAILED,
            AUTH_BY_VK_CLIENT,
            VK_CLIENT_NOT_FOUND;

            private static final /* synthetic */ EnumEntries kastatsbilkvmocb = EnumEntriesKt.enumEntries(kastatsbilkvmoca());

            @NotNull
            public static EnumEntries<EventType> getEntries() {
                return kastatsbilkvmocb;
            }
        }

        public TypeRegistrationItem(@NotNull EventType eventType, @Nullable String str, @Nullable Integer num, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable Long l10, @Nullable List<RegistrationFieldItem> list, @Nullable EventScreen eventScreen, @Nullable Integer num2, @Nullable String str5, @Nullable String str6, @Nullable Error error, @Nullable Integer num3, @Nullable Integer num4, @Nullable Integer num5) {
            Intrinsics.checkNotNullParameter(eventType, "eventType");
            this.eventType = eventType;
            this.sid = str;
            this.clientId = num;
            this.silentToken = str2;
            this.silentTokenUuid = str3;
            this.multiaccId = str4;
            this.userId = l10;
            this.fields = list;
            this.screenTo = eventScreen;
            this.errorSubcode = num2;
            this.flowSource = str5;
            this.flowEntryPoints = str6;
            this.error = error;
            this.authProviders = num3;
            this.appId = num4;
            this.authAppId = num5;
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final EventType getEventType() {
            return this.eventType;
        }

        @Nullable
        /* JADX INFO: renamed from: component10, reason: from getter */
        public final Integer getErrorSubcode() {
            return this.errorSubcode;
        }

        @Nullable
        /* JADX INFO: renamed from: component11, reason: from getter */
        public final String getFlowSource() {
            return this.flowSource;
        }

        @Nullable
        /* JADX INFO: renamed from: component12, reason: from getter */
        public final String getFlowEntryPoints() {
            return this.flowEntryPoints;
        }

        @Nullable
        /* JADX INFO: renamed from: component13, reason: from getter */
        public final Error getError() {
            return this.error;
        }

        @Nullable
        /* JADX INFO: renamed from: component14, reason: from getter */
        public final Integer getAuthProviders() {
            return this.authProviders;
        }

        @Nullable
        /* JADX INFO: renamed from: component15, reason: from getter */
        public final Integer getAppId() {
            return this.appId;
        }

        @Nullable
        /* JADX INFO: renamed from: component16, reason: from getter */
        public final Integer getAuthAppId() {
            return this.authAppId;
        }

        @Nullable
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getSid() {
            return this.sid;
        }

        @Nullable
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final Integer getClientId() {
            return this.clientId;
        }

        @Nullable
        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getSilentToken() {
            return this.silentToken;
        }

        @Nullable
        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getSilentTokenUuid() {
            return this.silentTokenUuid;
        }

        @Nullable
        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getMultiaccId() {
            return this.multiaccId;
        }

        @Nullable
        /* JADX INFO: renamed from: component7, reason: from getter */
        public final Long getUserId() {
            return this.userId;
        }

        @Nullable
        public final List<RegistrationFieldItem> component8() {
            return this.fields;
        }

        @Nullable
        /* JADX INFO: renamed from: component9, reason: from getter */
        public final EventScreen getScreenTo() {
            return this.screenTo;
        }

        @NotNull
        public final TypeRegistrationItem copy(@NotNull EventType eventType, @Nullable String sid, @Nullable Integer clientId, @Nullable String silentToken, @Nullable String silentTokenUuid, @Nullable String multiaccId, @Nullable Long userId, @Nullable List<RegistrationFieldItem> fields, @Nullable EventScreen screenTo, @Nullable Integer errorSubcode, @Nullable String flowSource, @Nullable String flowEntryPoints, @Nullable Error error, @Nullable Integer authProviders, @Nullable Integer appId, @Nullable Integer authAppId) {
            Intrinsics.checkNotNullParameter(eventType, "eventType");
            return new TypeRegistrationItem(eventType, sid, clientId, silentToken, silentTokenUuid, multiaccId, userId, fields, screenTo, errorSubcode, flowSource, flowEntryPoints, error, authProviders, appId, authAppId);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof TypeRegistrationItem)) {
                return false;
            }
            TypeRegistrationItem typeRegistrationItem = (TypeRegistrationItem) other;
            return this.eventType == typeRegistrationItem.eventType && Intrinsics.areEqual(this.sid, typeRegistrationItem.sid) && Intrinsics.areEqual(this.clientId, typeRegistrationItem.clientId) && Intrinsics.areEqual(this.silentToken, typeRegistrationItem.silentToken) && Intrinsics.areEqual(this.silentTokenUuid, typeRegistrationItem.silentTokenUuid) && Intrinsics.areEqual(this.multiaccId, typeRegistrationItem.multiaccId) && Intrinsics.areEqual(this.userId, typeRegistrationItem.userId) && Intrinsics.areEqual(this.fields, typeRegistrationItem.fields) && this.screenTo == typeRegistrationItem.screenTo && Intrinsics.areEqual(this.errorSubcode, typeRegistrationItem.errorSubcode) && Intrinsics.areEqual(this.flowSource, typeRegistrationItem.flowSource) && Intrinsics.areEqual(this.flowEntryPoints, typeRegistrationItem.flowEntryPoints) && this.error == typeRegistrationItem.error && Intrinsics.areEqual(this.authProviders, typeRegistrationItem.authProviders) && Intrinsics.areEqual(this.appId, typeRegistrationItem.appId) && Intrinsics.areEqual(this.authAppId, typeRegistrationItem.authAppId);
        }

        @Nullable
        public final Integer getAppId() {
            return this.appId;
        }

        @Nullable
        public final Integer getAuthAppId() {
            return this.authAppId;
        }

        @Nullable
        public final Integer getAuthProviders() {
            return this.authProviders;
        }

        @Nullable
        public final Integer getClientId() {
            return this.clientId;
        }

        @Nullable
        public final Error getError() {
            return this.error;
        }

        @Nullable
        public final Integer getErrorSubcode() {
            return this.errorSubcode;
        }

        @NotNull
        public final EventType getEventType() {
            return this.eventType;
        }

        @Nullable
        public final List<RegistrationFieldItem> getFields() {
            return this.fields;
        }

        @Nullable
        public final String getFlowEntryPoints() {
            return this.flowEntryPoints;
        }

        @Nullable
        public final String getFlowSource() {
            return this.flowSource;
        }

        @Nullable
        public final String getMultiaccId() {
            return this.multiaccId;
        }

        @Nullable
        public final EventScreen getScreenTo() {
            return this.screenTo;
        }

        @Nullable
        public final String getSid() {
            return this.sid;
        }

        @Nullable
        public final String getSilentToken() {
            return this.silentToken;
        }

        @Nullable
        public final String getSilentTokenUuid() {
            return this.silentTokenUuid;
        }

        @Nullable
        public final Long getUserId() {
            return this.userId;
        }

        public int hashCode() {
            int iHashCode = this.eventType.hashCode() * 31;
            String str = this.sid;
            int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
            Integer num = this.clientId;
            int iHashCode3 = (iHashCode2 + (num == null ? 0 : num.hashCode())) * 31;
            String str2 = this.silentToken;
            int iHashCode4 = (iHashCode3 + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.silentTokenUuid;
            int iHashCode5 = (iHashCode4 + (str3 == null ? 0 : str3.hashCode())) * 31;
            String str4 = this.multiaccId;
            int iHashCode6 = (iHashCode5 + (str4 == null ? 0 : str4.hashCode())) * 31;
            Long l10 = this.userId;
            int iHashCode7 = (iHashCode6 + (l10 == null ? 0 : l10.hashCode())) * 31;
            List<RegistrationFieldItem> list = this.fields;
            int iHashCode8 = (iHashCode7 + (list == null ? 0 : list.hashCode())) * 31;
            EventScreen eventScreen = this.screenTo;
            int iHashCode9 = (iHashCode8 + (eventScreen == null ? 0 : eventScreen.hashCode())) * 31;
            Integer num2 = this.errorSubcode;
            int iHashCode10 = (iHashCode9 + (num2 == null ? 0 : num2.hashCode())) * 31;
            String str5 = this.flowSource;
            int iHashCode11 = (iHashCode10 + (str5 == null ? 0 : str5.hashCode())) * 31;
            String str6 = this.flowEntryPoints;
            int iHashCode12 = (iHashCode11 + (str6 == null ? 0 : str6.hashCode())) * 31;
            Error error = this.error;
            int iHashCode13 = (iHashCode12 + (error == null ? 0 : error.hashCode())) * 31;
            Integer num3 = this.authProviders;
            int iHashCode14 = (iHashCode13 + (num3 == null ? 0 : num3.hashCode())) * 31;
            Integer num4 = this.appId;
            int iHashCode15 = (iHashCode14 + (num4 == null ? 0 : num4.hashCode())) * 31;
            Integer num5 = this.authAppId;
            return iHashCode15 + (num5 != null ? num5.hashCode() : 0);
        }

        @NotNull
        public String toString() {
            return "TypeRegistrationItem(eventType=" + this.eventType + ", sid=" + this.sid + ", clientId=" + this.clientId + ", silentToken=" + this.silentToken + ", silentTokenUuid=" + this.silentTokenUuid + ", multiaccId=" + this.multiaccId + ", userId=" + this.userId + ", fields=" + this.fields + ", screenTo=" + this.screenTo + ", errorSubcode=" + this.errorSubcode + ", flowSource=" + this.flowSource + ", flowEntryPoints=" + this.flowEntryPoints + ", error=" + this.error + ", authProviders=" + this.authProviders + ", appId=" + this.appId + ", authAppId=" + this.authAppId + ')';
        }

        public /* synthetic */ TypeRegistrationItem(EventType eventType, String str, Integer num, String str2, String str3, String str4, Long l10, List list, EventScreen eventScreen, Integer num2, String str5, String str6, Error error, Integer num3, Integer num4, Integer num5, int i10, DefaultConstructorMarker defaultConstructorMarker) {
            this(eventType, (i10 & 2) != 0 ? null : str, (i10 & 4) != 0 ? null : num, (i10 & 8) != 0 ? null : str2, (i10 & 16) != 0 ? null : str3, (i10 & 32) != 0 ? null : str4, (i10 & 64) != 0 ? null : l10, (i10 & 128) != 0 ? null : list, (i10 & 256) != 0 ? null : eventScreen, (i10 & 512) != 0 ? null : num2, (i10 & 1024) != 0 ? null : str5, (i10 & 2048) != 0 ? null : str6, (i10 & 4096) != 0 ? null : error, (i10 & 8192) != 0 ? null : num3, (i10 & 16384) != 0 ? null : num4, (i10 & 32768) != 0 ? null : num5);
        }
    }
}
