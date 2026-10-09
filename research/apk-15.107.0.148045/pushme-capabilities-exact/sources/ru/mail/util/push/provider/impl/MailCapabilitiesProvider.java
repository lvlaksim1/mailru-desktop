package ru.mail.util.push.provider.impl;

import androidx.compose.runtime.internal.StabilityInferred;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Transformer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.logic.pushfilters.FilterAccessor;
import ru.mail.logic.pushfilters.PushFilter;
import ru.mail.logic.pushfilters.PushFilterItem;
import ru.mail.portal.app.adapter.notifications.tags.Tag;
import ru.mail.util.log.Log;
import ru.mail.util.push.provider.CapabilitiesProvider;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00182\u00020\u0001:\u0002\u0017\u0018B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0007\u0010\bJ\u001e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u0016J\u0018\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\rH\u0002J\u001a\u0010\u0012\u001a\u00020\u0013*\u00020\u000b2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u0002J\f\u0010\u0015\u001a\u00020\u0016*\u00020\u0003H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\t¨\u0006\u0019"}, d2 = {"Lru/mail/util/push/provider/impl/MailCapabilitiesProvider;", "Lru/mail/util/push/provider/CapabilitiesProvider;", "needPushMsg", "", "accessor", "Lru/mail/logic/pushfilters/FilterAccessor;", "isEnabledImportantReminder", "<init>", "(ZLru/mail/logic/pushfilters/FilterAccessor;Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "getCapabilities", "Lorg/json/JSONObject;", "userIdentifier", "", "enabledTags", "", "Lru/mail/portal/app/adapter/notifications/tags/Tag;", "getCanMailJson", "addEnabledTags", "", "tags", "toCapabilitiesValue", "", MailCapabilitiesProvider.JSON_KEY_FILTER, "Companion", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nMailCapabilitiesProvider.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MailCapabilitiesProvider.kt\nru/mail/util/push/provider/impl/MailCapabilitiesProvider\n+ 2 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,165:1\n37#2,2:166\n37#2,2:168\n37#2,2:170\n1563#3:172\n1634#3,3:173\n*S KotlinDebug\n*F\n+ 1 MailCapabilitiesProvider.kt\nru/mail/util/push/provider/impl/MailCapabilitiesProvider\n*L\n69#1:166,2\n74#1:168,2\n79#1:170,2\n100#1:172\n100#1:173,3\n*E\n"})
public final class MailCapabilitiesProvider implements CapabilitiesProvider {
    private static final int CAN_MAIL_DISABLED_VALUE = 0;

    @NotNull
    private static final String JSON_KEY_ACTUAL_SUPPORT = "actual_support";

    @NotNull
    private static final String JSON_KEY_CAN_MAIL = "can_mail";

    @NotNull
    private static final String JSON_KEY_EXCLUDE_LIST = "excludeList";

    @NotNull
    private static final String JSON_KEY_FILTER = "Filter";

    @NotNull
    private static final String JSON_KEY_FILTER_LIST = "filterList";

    @NotNull
    private static final String JSON_KEY_FOLDER = "Folder";

    @NotNull
    private static final String JSON_KEY_SOCIAL_NETWORK = "SocialNetwork";

    @NotNull
    private static final String JSON_KEY_SOCIAL_SERVICE = "SocialService";

    @NotNull
    private static final String JSON_KEY_TAGS = "tags";

    @NotNull
    private final FilterAccessor accessor;

    @Nullable
    private final Boolean isEnabledImportantReminder;
    private final boolean needPushMsg;
    public static final int $stable = 8;

    @NotNull
    private static final Log LOG = Log.INSTANCE.getLog("MailCapabilitiesProvider");

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0006\u0010\u000b\u001a\u00020\fJ\b\u0010\u0011\u001a\u00020\u0003H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\nR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\u00020\u000e8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0013"}, d2 = {"Lru/mail/util/push/provider/impl/MailCapabilitiesProvider$Filter;", "", "arrayName", "", "options", "", "enabled", "", "<init>", "(Ljava/lang/String;[Ljava/lang/Object;Z)V", "[Ljava/lang/Object;", "toJson", "Lorg/json/JSONObject;", "jsonIds", "Lorg/json/JSONArray;", "getJsonIds", "()Lorg/json/JSONArray;", "toString", "Companion", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Filter {

        @NotNull
        public static final String JSON_KEY_ENABLED = "enabled";

        @NotNull
        private final String arrayName;
        private final boolean enabled;

        @Nullable
        private final Object[] options;

        public Filter(@NotNull String arrayName, @Nullable Object[] objArr, boolean z10) {
            Intrinsics.checkNotNullParameter(arrayName, "arrayName");
            this.arrayName = arrayName;
            this.options = objArr;
            this.enabled = z10;
        }

        private final JSONArray getJsonIds() {
            return this.options == null ? new JSONArray() : new JSONArray(Arrays.toString(this.options));
        }

        @NotNull
        public final JSONObject toJson() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(this.arrayName, getJsonIds());
                jSONObject.put("enabled", this.enabled);
                return jSONObject;
            } catch (JSONException e10) {
                e10.printStackTrace();
                return jSONObject;
            }
        }

        @NotNull
        public String toString() {
            String string = toJson().toString();
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
            return string;
        }
    }

    public MailCapabilitiesProvider(boolean z10, @NotNull FilterAccessor accessor, @Nullable Boolean bool) {
        Intrinsics.checkNotNullParameter(accessor, "accessor");
        this.needPushMsg = z10;
        this.accessor = accessor;
        this.isEnabledImportantReminder = bool;
    }

    private final void addEnabledTags(JSONObject jSONObject, Collection<? extends Tag> collection) throws JSONException {
        if (collection.isEmpty()) {
            return;
        }
        Collection<? extends Tag> collection2 = collection;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(collection2, 10));
        Iterator<T> it = collection2.iterator();
        while (it.hasNext()) {
            arrayList.add(Integer.valueOf(((Tag) it.next()).getIdForPusher()));
        }
        jSONObject.put("tags", new JSONArray((Collection) arrayList));
    }

    private final JSONObject getCanMailJson(FilterAccessor accessor, String userIdentifier) {
        PushFilter.Type type = PushFilter.Type.FOLDER;
        Collection collectionSelect = CollectionUtils.select(accessor.get(type), new FilterAccessor.PushFilterByParams(userIdentifier, false));
        Transformer<PushFilterItem, Long> transformer = FilterAccessor.CONVERTER_FILTERS_TO_ITEM_ID;
        Collection collectionCollect = CollectionUtils.collect(collectionSelect, transformer);
        PushFilter.Type type2 = PushFilter.Type.SOCIAL;
        Collection collectionCollect2 = CollectionUtils.collect(CollectionUtils.select(accessor.get(type2), new FilterAccessor.PushFilterByParams(true)), transformer);
        PushFilter.Type type3 = PushFilter.Type.SERVICE;
        Collection collectionCollect3 = CollectionUtils.collect(CollectionUtils.select(accessor.get(type3), new FilterAccessor.PushFilterByParams(true)), transformer);
        boolean state = accessor.getGroupFilter(type).getState();
        boolean state2 = accessor.getGroupFilter(type2).getState();
        boolean state3 = accessor.getGroupFilter(type3).getState();
        Intrinsics.checkNotNull(collectionCollect);
        Filter filter = new Filter(JSON_KEY_FILTER_LIST, collectionCollect.toArray(new Object[0]), state);
        Intrinsics.checkNotNull(collectionCollect2);
        Filter filter2 = new Filter(JSON_KEY_EXCLUDE_LIST, collectionCollect2.toArray(new Object[0]), state2);
        Intrinsics.checkNotNull(collectionCollect3);
        Filter filter3 = new Filter(JSON_KEY_EXCLUDE_LIST, collectionCollect3.toArray(new Object[0]), state3);
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put(JSON_KEY_FOLDER, filter.toJson());
            jSONObject2.put(JSON_KEY_SOCIAL_NETWORK, filter2.toJson());
            jSONObject2.put(JSON_KEY_SOCIAL_SERVICE, filter3.toJson());
            jSONObject.put(JSON_KEY_FILTER, jSONObject2);
            return jSONObject;
        } catch (JSONException e10) {
            LOG.e("Error", e10);
            return jSONObject;
        }
    }

    @Override // ru.mail.util.push.provider.CapabilitiesProvider
    @NotNull
    public JSONObject getCapabilities(@NotNull String userIdentifier, @NotNull Collection<? extends Tag> enabledTags) {
        Intrinsics.checkNotNullParameter(userIdentifier, "userIdentifier");
        Intrinsics.checkNotNullParameter(enabledTags, "enabledTags");
        JSONObject jSONObject = new JSONObject();
        try {
            if (this.needPushMsg) {
                jSONObject.put(JSON_KEY_CAN_MAIL, getCanMailJson(this.accessor, userIdentifier));
            } else {
                jSONObject.put(JSON_KEY_CAN_MAIL, 0);
            }
            Boolean bool = this.isEnabledImportantReminder;
            if (bool != null) {
                jSONObject.put(JSON_KEY_ACTUAL_SUPPORT, toCapabilitiesValue(bool.booleanValue()));
            }
            addEnabledTags(jSONObject, enabledTags);
            return jSONObject;
        } catch (JSONException e10) {
            LOG.e("Failed to construct capabilities", e10);
            return jSONObject;
        }
    }

    private final int toCapabilitiesValue(boolean z10) {
        return z10 ? 1 : 0;
    }
}
