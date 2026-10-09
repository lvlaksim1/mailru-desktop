package ru.mail.portal.app.adapter.notifications.tags;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import ru.mail.kotlett.divkit.VariableConstants;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u001e\n\u0002\b\u0004\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\rH\u0016J\u001e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00020\r2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0016J\u0018\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u000bH\u0016J\u0018\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u000bH\u0016J\u0010\u0010\u0014\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\rH\u0002J\u0016\u0010\u0015\u001a\u00020\r2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0016H\u0002J\u0016\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\u0018\u001a\u00020\rH\u0002R\u0016\u0010\u0006\u001a\n \b*\u0004\u0018\u00010\u00070\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lru/mail/portal/app/adapter/notifications/tags/SharedPrefsAppTagsStorage;", "Lru/mail/portal/app/adapter/notifications/tags/AppTagsStorage;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "prefs", "Landroid/content/SharedPreferences;", "kotlin.jvm.PlatformType", "getDisabledTagIds", "", "", "appId", "", "saveDisabledTags", "", "tagIds", "setTagEnabled", "tagId", "setTagDisabled", "constructKey", "convertTagsToString", "", "parseTagsFromString", VariableConstants.TYPE_STRING, "Companion", "app-adapter_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nSharedPrefsAppTagsStorage.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SharedPrefsAppTagsStorage.kt\nru/mail/portal/app/adapter/notifications/tags/SharedPrefsAppTagsStorage\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,61:1\n774#2:62\n865#2,2:63\n1617#2,9:65\n1869#2:74\n1870#2:76\n1626#2:77\n1#3:75\n*S KotlinDebug\n*F\n+ 1 SharedPrefsAppTagsStorage.kt\nru/mail/portal/app/adapter/notifications/tags/SharedPrefsAppTagsStorage\n*L\n31#1:62\n31#1:63,2\n53#1:65,9\n53#1:74\n53#1:76\n53#1:77\n53#1:75\n*E\n"})
public final class SharedPrefsAppTagsStorage implements AppTagsStorage {

    @NotNull
    private static final String KEY_PREFIX = "portal_app_disabled_tags_";

    @NotNull
    private static final String TAGS_SEPARATOR = ",";
    private final SharedPreferences prefs;

    public SharedPrefsAppTagsStorage(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.prefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    private final String constructKey(String appId) {
        return KEY_PREFIX + appId;
    }

    private final String convertTagsToString(Collection<Integer> tagIds) {
        return CollectionsKt.joinToString$default(tagIds, ",", null, null, 0, null, null, 62, null);
    }

    private final Set<Integer> parseTagsFromString(String string) {
        List listSplit$default = StringsKt.split$default((CharSequence) string, new String[]{","}, false, 0, 6, (Object) null);
        ArrayList arrayList = new ArrayList();
        Iterator it = listSplit$default.iterator();
        while (it.hasNext()) {
            Integer intOrNull = StringsKt.toIntOrNull((String) it.next());
            if (intOrNull != null) {
                arrayList.add(intOrNull);
            }
        }
        return CollectionsKt.toSet(arrayList);
    }

    @Override // ru.mail.portal.app.adapter.notifications.tags.AppTagsStorage
    @NotNull
    public Set<Integer> getDisabledTagIds(@NotNull String appId) {
        Intrinsics.checkNotNullParameter(appId, "appId");
        String string = this.prefs.getString(constructKey(appId), null);
        return (string == null || StringsKt.isBlank(string)) ? SetsKt.emptySet() : parseTagsFromString(string);
    }

    @Override // ru.mail.portal.app.adapter.notifications.tags.AppTagsStorage
    public void saveDisabledTags(@NotNull String appId, @NotNull Set<Integer> tagIds) {
        Intrinsics.checkNotNullParameter(appId, "appId");
        Intrinsics.checkNotNullParameter(tagIds, "tagIds");
        String strConstructKey = constructKey(appId);
        if (tagIds.isEmpty()) {
            this.prefs.edit().remove(strConstructKey).apply();
        } else {
            this.prefs.edit().putString(strConstructKey, convertTagsToString(tagIds)).apply();
        }
    }

    @Override // ru.mail.portal.app.adapter.notifications.tags.AppTagsStorage
    public void setTagDisabled(@NotNull String appId, int tagId) {
        Intrinsics.checkNotNullParameter(appId, "appId");
        Set<Integer> disabledTagIds = getDisabledTagIds(appId);
        if (disabledTagIds.contains(Integer.valueOf(tagId))) {
            return;
        }
        saveDisabledTags(appId, SetsKt.plus(disabledTagIds, Integer.valueOf(tagId)));
    }

    @Override // ru.mail.portal.app.adapter.notifications.tags.AppTagsStorage
    public void setTagEnabled(@NotNull String appId, int tagId) {
        Intrinsics.checkNotNullParameter(appId, "appId");
        Set<Integer> disabledTagIds = getDisabledTagIds(appId);
        if (disabledTagIds.contains(Integer.valueOf(tagId))) {
            ArrayList arrayList = new ArrayList();
            for (Object obj : disabledTagIds) {
                if (((Number) obj).intValue() != tagId) {
                    arrayList.add(obj);
                }
            }
            saveDisabledTags(appId, CollectionsKt.toSet(arrayList));
        }
    }
}
