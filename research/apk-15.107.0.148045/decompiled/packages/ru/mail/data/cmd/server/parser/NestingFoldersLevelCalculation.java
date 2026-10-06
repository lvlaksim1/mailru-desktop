package ru.mail.data.cmd.server.parser;

import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \n2\u00020\u0001:\u0001\nB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0014\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007H\u0014R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lru/mail/data/cmd/server/parser/NestingFoldersLevelCalculation;", "Lru/mail/data/cmd/server/parser/BaseNestingLevelsCalculation;", "jsonArray", "Lorg/json/JSONArray;", "<init>", "(Lorg/json/JSONArray;)V", "buildParentLinks", "", "Lru/mail/data/cmd/server/parser/BaseNestingLevelsCalculation$ChildId;", "Lru/mail/data/cmd/server/parser/BaseNestingLevelsCalculation$ParentId;", "Companion", "folder_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nNestingFoldersLevelCalculation.kt\nKotlin\n*S Kotlin\n*F\n+ 1 NestingFoldersLevelCalculation.kt\nru/mail/data/cmd/server/parser/NestingFoldersLevelCalculation\n+ 2 UtilExtensions.kt\nru/mail/utils/UtilExtensionsKt\n*L\n1#1,28:1\n76#2,4:29\n*S KotlinDebug\n*F\n+ 1 NestingFoldersLevelCalculation.kt\nru/mail/data/cmd/server/parser/NestingFoldersLevelCalculation\n*L\n13#1:29,4\n*E\n"})
public final class NestingFoldersLevelCalculation extends BaseNestingLevelsCalculation {

    @NotNull
    private static final String FOLDER_ID = "id";

    @NotNull
    private static final String PARENT_FOLDER_ID = "parent";

    @NotNull
    private final JSONArray jsonArray;

    public NestingFoldersLevelCalculation(@NotNull JSONArray jsonArray) {
        Intrinsics.checkNotNullParameter(jsonArray, "jsonArray");
        this.jsonArray = jsonArray;
    }

    @Override // ru.mail.data.cmd.server.parser.BaseNestingLevelsCalculation
    @NotNull
    protected Map<BaseNestingLevelsCalculation.ChildId, BaseNestingLevelsCalculation.ParentId> buildParentLinks() throws JSONException {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        JSONArray jSONArray = this.jsonArray;
        int length = jSONArray.length();
        for (int i10 = 0; i10 < length; i10++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i10);
            Intrinsics.checkNotNullExpressionValue(jSONObject, "getJSONObject(...)");
            long jOptLong = jSONObject.optLong("id", -1L);
            if (jOptLong != -1) {
                linkedHashMap.put(BaseNestingLevelsCalculation.ChildId.m15539boximpl(BaseNestingLevelsCalculation.ChildId.m15540constructorimpl(jOptLong)), BaseNestingLevelsCalculation.ParentId.m15546boximpl(BaseNestingLevelsCalculation.ParentId.m15547constructorimpl(jSONObject.optLong("parent", -1L))));
            }
        }
        return linkedHashMap;
    }
}
