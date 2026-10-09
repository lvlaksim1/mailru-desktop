package ru.mail.ui.fragments.settings;

import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.preference.MultiSelectListPreference;
import android.preference.Preference;
import androidx.compose.runtime.internal.StabilityInferred;
import androidx.preference.PreferenceManager;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import ru.mail.locator.Locator;
import ru.mail.util.log.Log;
import ru.mail.util.push.PushMessagesTransport;
import ru.mail.util.push.PushType;
import ru.mail.util.push.component.PushComponent;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0007\u0018\u0000 %2\u00020\u0001:\u0002$%B\u001b\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\u0016\u0010\u0010\u001a\u00020\t2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012H\u0002J\u0010\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0012H\u0002J\u0010\u0010\u0015\u001a\u00020\t2\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J2\u0010\u0018\u001a\u00020\u00172\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012H\u0002J\u0018\u0010\u001e\u001a\u00020\t2\u0006\u0010\u001f\u001a\u00020\u00132\u0006\u0010 \u001a\u00020\rH\u0002J\u0012\u0010!\u001a\u0004\u0018\u00010\u00132\u0006\u0010\"\u001a\u00020\u001bH\u0002J\u000e\u0010#\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001aH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lru/mail/ui/fragments/settings/DevSettingsUsedPushTransportsDelegate;", "", "context", "Landroid/content/Context;", "prefs", "Landroid/content/SharedPreferences;", "<init>", "(Landroid/content/Context;Landroid/content/SharedPreferences;)V", "setup", "", "preference", "Landroid/preference/MultiSelectListPreference;", "isPushTransportEnabledInDevSettings", "", "type", "Lru/mail/util/push/PushType;", "saveEnabledTransports", "value", "", "", "loadEnabledTransports", "applyChanges", "diff", "Lru/mail/ui/fragments/settings/DevSettingsUsedPushTransportsDelegate$Diff;", "calculateDiff", "allTransports", "", "Lru/mail/util/push/PushMessagesTransport;", "before", "after", "enableComponent", "cls", "enable", "getClassName", "transport", "getAllTransports", "Diff", "Companion", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nDevSettingsUsedPushTransportsDelegate.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DevSettingsUsedPushTransportsDelegate.kt\nru/mail/ui/fragments/settings/DevSettingsUsedPushTransportsDelegate\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,125:1\n1563#2:126\n1634#2,3:127\n774#2:135\n865#2,2:136\n774#2:138\n865#2,2:139\n37#3,2:130\n37#3,2:132\n1#4:134\n*S KotlinDebug\n*F\n+ 1 DevSettingsUsedPushTransportsDelegate.kt\nru/mail/ui/fragments/settings/DevSettingsUsedPushTransportsDelegate\n*L\n23#1:126\n23#1:127,3\n81#1:135\n81#1:136,2\n82#1:138\n82#1:139,2\n25#1:130,2\n26#1:132,2\n*E\n"})
public final class DevSettingsUsedPushTransportsDelegate {

    @NotNull
    private static final String PREF_KEY = "ru.mail.used_push_transports";

    @NotNull
    private final Context context;

    @NotNull
    private final SharedPreferences prefs;
    public static final int $stable = 8;

    @NotNull
    private static final Log LOG = Log.INSTANCE.getLog("DevSettingsUsedPushTransportsDelegate");

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u0001B#\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0006\u0010\u0007R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\t¨\u0006\u000b"}, d2 = {"Lru/mail/ui/fragments/settings/DevSettingsUsedPushTransportsDelegate$Diff;", "", "shouldBeEnabled", "", "Lru/mail/util/push/PushMessagesTransport;", "shouldBeDisabled", "<init>", "(Ljava/util/Collection;Ljava/util/Collection;)V", "getShouldBeEnabled", "()Ljava/util/Collection;", "getShouldBeDisabled", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Diff {

        @NotNull
        private final Collection<PushMessagesTransport> shouldBeDisabled;

        @NotNull
        private final Collection<PushMessagesTransport> shouldBeEnabled;

        /* JADX WARN: Multi-variable type inference failed */
        public Diff(@NotNull Collection<? extends PushMessagesTransport> shouldBeEnabled, @NotNull Collection<? extends PushMessagesTransport> shouldBeDisabled) {
            Intrinsics.checkNotNullParameter(shouldBeEnabled, "shouldBeEnabled");
            Intrinsics.checkNotNullParameter(shouldBeDisabled, "shouldBeDisabled");
            this.shouldBeEnabled = shouldBeEnabled;
            this.shouldBeDisabled = shouldBeDisabled;
        }

        @NotNull
        public final Collection<PushMessagesTransport> getShouldBeDisabled() {
            return this.shouldBeDisabled;
        }

        @NotNull
        public final Collection<PushMessagesTransport> getShouldBeEnabled() {
            return this.shouldBeEnabled;
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[PushType.values().length];
            try {
                iArr[PushType.GCM.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[PushType.HMS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[PushType.VKPNS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[PushType.STUB.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    @JvmOverloads
    public DevSettingsUsedPushTransportsDelegate(@NotNull Context context) {
        this(context, null, 2, 0 == true ? 1 : 0);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    private final void applyChanges(Diff diff) {
        for (PushMessagesTransport pushMessagesTransport : diff.getShouldBeEnabled()) {
            LOG.i("Enabling " + pushMessagesTransport.getPushType().name() + " push transport");
            String className = getClassName(pushMessagesTransport);
            if (className != null) {
                enableComponent(className, true);
            }
            pushMessagesTransport.register();
        }
        for (PushMessagesTransport pushMessagesTransport2 : diff.getShouldBeDisabled()) {
            LOG.i("Disabling " + pushMessagesTransport2.getPushType().name() + " push transport");
            String className2 = getClassName(pushMessagesTransport2);
            if (className2 != null) {
                enableComponent(className2, false);
            }
            pushMessagesTransport2.unregister();
        }
    }

    private final Diff calculateDiff(Collection<? extends PushMessagesTransport> allTransports, Set<String> before, Set<String> after) {
        Set setMinus = SetsKt.minus((Set) after, (Iterable) before);
        Set setMinus2 = SetsKt.minus((Set) before, (Iterable) after);
        Collection<? extends PushMessagesTransport> collection = allTransports;
        ArrayList arrayList = new ArrayList();
        for (Object obj : collection) {
            if (setMinus.contains(((PushMessagesTransport) obj).getPushType().name())) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        for (Object obj2 : collection) {
            if (setMinus2.contains(((PushMessagesTransport) obj2).getPushType().name())) {
                arrayList2.add(obj2);
            }
        }
        return new Diff(arrayList, arrayList2);
    }

    private final void enableComponent(String cls, boolean enable) {
        PackageManager packageManager = this.context.getPackageManager();
        Intrinsics.checkNotNullExpressionValue(packageManager, "getPackageManager(...)");
        packageManager.setComponentEnabledSetting(new ComponentName(this.context.getPackageName(), cls), enable ? 1 : 2, 1);
    }

    private final Collection<PushMessagesTransport> getAllTransports() {
        return ((PushComponent) Locator.INSTANCE.from(this.context).locate(PushComponent.class)).getPushMessagesTransports();
    }

    private final String getClassName(PushMessagesTransport transport) {
        int i10 = WhenMappings.$EnumSwitchMapping$0[transport.getPushType().ordinal()];
        if (i10 == 1) {
            return "ru.mail.util.push.gcm.MailMessagingService";
        }
        if (i10 == 2) {
            return "ru.mail.util.push.huawei.MailMessagingService";
        }
        if (i10 == 3) {
            return "ru.mail.rustoresdk.MailMessagingService";
        }
        if (i10 == 4) {
            return null;
        }
        throw new NoWhenBranchMatchedException();
    }

    private final Set<String> loadEnabledTransports() {
        return this.prefs.getStringSet(PREF_KEY, null);
    }

    private final void saveEnabledTransports(Set<String> value) {
        this.prefs.edit().putStringSet(PREF_KEY, value).apply();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final boolean setup$lambda$1(DevSettingsUsedPushTransportsDelegate devSettingsUsedPushTransportsDelegate, Set set, Collection collection, Preference preference, Object obj) {
        Set<String> setLoadEnabledTransports = devSettingsUsedPushTransportsDelegate.loadEnabledTransports();
        if (setLoadEnabledTransports != null) {
            set = setLoadEnabledTransports;
        }
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.collections.Set<kotlin.String>");
        Set set2 = (Set) obj;
        LOG.i("Change detected: previous value: " + set + ", new: " + set2);
        devSettingsUsedPushTransportsDelegate.saveEnabledTransports(set2);
        devSettingsUsedPushTransportsDelegate.applyChanges(devSettingsUsedPushTransportsDelegate.calculateDiff(collection, set, set2));
        return true;
    }

    public final boolean isPushTransportEnabledInDevSettings(@NotNull PushType type) {
        Intrinsics.checkNotNullParameter(type, "type");
        Set<String> setLoadEnabledTransports = loadEnabledTransports();
        if (setLoadEnabledTransports == null) {
            return true;
        }
        return setLoadEnabledTransports.contains(type.name());
    }

    public final void setup(@NotNull MultiSelectListPreference preference) {
        Intrinsics.checkNotNullParameter(preference, "preference");
        final Collection<PushMessagesTransport> allTransports = getAllTransports();
        Collection<PushMessagesTransport> collection = allTransports;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(collection, 10));
        Iterator<T> it = collection.iterator();
        while (it.hasNext()) {
            arrayList.add(((PushMessagesTransport) it.next()).getPushType().name());
        }
        final Set<String> set = CollectionsKt.toSet(arrayList);
        Set<String> set2 = set;
        preference.setEntries((CharSequence[]) set2.toArray(new String[0]));
        preference.setEntryValues((CharSequence[]) set2.toArray(new String[0]));
        Set<String> setLoadEnabledTransports = loadEnabledTransports();
        if (setLoadEnabledTransports == null) {
            setLoadEnabledTransports = set;
        }
        preference.setValues(setLoadEnabledTransports);
        preference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() { // from class: ru.mail.ui.fragments.settings.u
            @Override // android.preference.Preference.OnPreferenceChangeListener
            public final boolean onPreferenceChange(Preference preference2, Object obj) {
                return DevSettingsUsedPushTransportsDelegate.setup$lambda$1(this.f100305a, set, allTransports, preference2, obj);
            }
        });
    }

    @JvmOverloads
    public DevSettingsUsedPushTransportsDelegate(@NotNull Context context, @NotNull SharedPreferences prefs) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(prefs, "prefs");
        this.context = context;
        this.prefs = prefs;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ DevSettingsUsedPushTransportsDelegate(Context context, SharedPreferences sharedPreferences, int i10, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i10 & 2) != 0) {
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
            Intrinsics.checkNotNullExpressionValue(sharedPreferences, "getDefaultSharedPreferences(...)");
        }
        this(context, sharedPreferences);
    }
}
