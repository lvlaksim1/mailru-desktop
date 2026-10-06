package ru.mail.data.cmd.server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.auth.request.AccountInfo;
import ru.mail.kit.shortcut.deeplink.ShortcutContract;
import ru.mail.logic.cmd.MarkOperation;
import ru.mail.network.HttpMethod;
import ru.mail.network.Param;
import ru.mail.search.metasearch.util.analytics.AnalyticsUtilsExtKt;
import ru.mail.serverapi.FolderState;
import ru.mail.serverapi.ServerCommandBaseParams;
import ru.mail.util.log.Log;
import ru.mail.utils.CollectionUtils;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
public class MarkCommandBaseParams<T> extends ServerCommandBaseParams {
    private static final Log LOG = Log.getLog("MarkCommandBaseParams");
    private static final String PARAM_KEY_MARKS = "marks";

    @Param(getterName = "getMarks", method = HttpMethod.POST, name = PARAM_KEY_MARKS, useGetter = true)
    private String mMarks;
    private final Map<MarkOperation, Map<Long, List<T>>> mOperations;

    /* JADX INFO: compiled from: ProGuard */
    public static abstract class Builder<T> {
        final Map<MarkOperation, Map<Long, List<T>>> mOperationMap = new HashMap();

        public void add(MarkOperation markOperation, T t10) {
            add(markOperation, t10, null);
        }

        public Map<MarkOperation, Map<Long, List<T>>> getOperations() {
            return this.mOperationMap;
        }

        public void add(MarkOperation markOperation, T t10, Long l10) {
            Map<Long, List<T>> map = this.mOperationMap.get(markOperation);
            if (map == null) {
                map = new HashMap<>();
                this.mOperationMap.put(markOperation, map);
            }
            List<T> arrayList = map.get(l10);
            if (arrayList == null) {
                arrayList = new ArrayList<>();
                map.put(l10, arrayList);
            }
            arrayList.add(t10);
        }
    }

    public MarkCommandBaseParams(@NotNull Map<MarkOperation, Map<Long, List<T>>> map, @NotNull AccountInfo accountInfo, @Nullable FolderState folderState) {
        super(accountInfo, folderState);
        this.mOperations = map;
    }

    private void addMarks(MarkOperation markOperation, MarkOperation markOperation2, String str, JSONArray jSONArray) throws JSONException {
        for (Long l10 : getKeys(markOperation, markOperation2)) {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("name", str);
            jSONObject.put(markOperation.getMethod(), getIdJsonArray(markOperation, l10));
            jSONObject.put(markOperation2.getMethod(), getIdJsonArray(markOperation2, l10));
            if (l10 != null) {
                jSONObject.put("folder", l10);
            }
            jSONArray.put(jSONObject);
        }
    }

    private List<Long> getKeys(MarkOperation markOperation, MarkOperation markOperation2) {
        return (this.mOperations.containsKey(markOperation) || this.mOperations.containsKey(markOperation2)) ? CollectionUtils.getMapKeys(this.mOperations.get(markOperation), this.mOperations.get(markOperation2)) : Collections.EMPTY_LIST;
    }

    @Override // ru.mail.serverapi.ServerCommandBaseParams
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj instanceof MarkCommandBaseParams) && super.equals(obj)) {
            return this.mOperations.equals(((MarkCommandBaseParams) obj).mOperations);
        }
        return false;
    }

    public JSONArray getIdJsonArray(MarkOperation markOperation, Long l10) {
        Map<Long, List<T>> map;
        List<T> list;
        JSONArray jSONArray = new JSONArray();
        if (this.mOperations.containsKey(markOperation) && (map = this.mOperations.get(markOperation)) != null && !map.isEmpty() && (list = map.get(l10)) != null && !list.isEmpty()) {
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                jSONArray.put(it.next());
            }
        }
        return jSONArray;
    }

    public String getMarks() {
        JSONArray jSONArray = new JSONArray();
        try {
            addMarks(MarkOperation.UNREAD_SET, MarkOperation.UNREAD_UNSET, AnalyticsUtilsExtKt.FILTER_UNREAD, jSONArray);
            addMarks(MarkOperation.FLAG_SET, MarkOperation.FLAG_UNSET, AnalyticsUtilsExtKt.FILTER_FLAGGED, jSONArray);
            addMarks(MarkOperation.PIN_SET, MarkOperation.PIN_UNSET, ShortcutContract.TYPE_PINNED, jSONArray);
        } catch (JSONException e10) {
            LOG.e(e10.getMessage(), e10);
        }
        return jSONArray.toString();
    }

    @Override // ru.mail.serverapi.ServerCommandBaseParams
    public int hashCode() {
        return (super.hashCode() * 31) + this.mOperations.hashCode();
    }
}
