package ru.mail.data.cmd.server;

import android.content.Context;
import androidx.compose.runtime.internal.StabilityInferred;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;
import ru.mail.asserter.core.AsserterConfigFactory;
import ru.mail.asserter.core.AsserterFactory;
import ru.mail.asserter.description.Descriptions;
import ru.mail.locator.Locator;
import ru.mail.network.NetworkCommand;
import ru.mail.util.log.LogCollector;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@StabilityInferred(parameters = 1)
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0007J\u0016\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\r\u001a\u00020\u000eH\u0002¨\u0006\u000f"}, d2 = {"Lru/mail/data/cmd/server/TornadoSendInlineImagesErrorsAsserter;", "", "<init>", "()V", "assertHasNotExistsInlineImages", "", "context", "Landroid/content/Context;", "response", "Lru/mail/network/NetworkCommand$Response;", "collectKeys", "", "", "bodyObj", "Lorg/json/JSONObject;", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nTornadoSendInlineImagesErrorsAsserter.kt\nKotlin\n*S Kotlin\n*F\n+ 1 TornadoSendInlineImagesErrorsAsserter.kt\nru/mail/data/cmd/server/TornadoSendInlineImagesErrorsAsserter\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 Iterators.kt\nkotlin/collections/CollectionsKt__IteratorsKt\n*L\n1#1,65:1\n1761#2,3:66\n32#3,2:69\n*S KotlinDebug\n*F\n+ 1 TornadoSendInlineImagesErrorsAsserter.kt\nru/mail/data/cmd/server/TornadoSendInlineImagesErrorsAsserter\n*L\n30#1:66,3\n56#1:69,2\n*E\n"})
public final class TornadoSendInlineImagesErrorsAsserter {
    public static final int $stable = 0;

    @NotNull
    public static final TornadoSendInlineImagesErrorsAsserter INSTANCE = new TornadoSendInlineImagesErrorsAsserter();

    private TornadoSendInlineImagesErrorsAsserter() {
    }

    @JvmStatic
    public static final void assertHasNotExistsInlineImages(@NotNull Context context, @Nullable NetworkCommand.Response response) {
        String respString;
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            if (response == null || (respString = response.getRespString()) == null) {
                respString = "";
            }
            String strOptString = new JSONObject(respString).optString("body");
            if (strOptString == null || StringsKt.isBlank(strOptString)) {
                return;
            }
            JSONObject jSONObject = new JSONObject(strOptString);
            List<String> listCollectKeys = INSTANCE.collectKeys(jSONObject);
            if ((listCollectKeys instanceof Collection) && listCollectKeys.isEmpty()) {
                return;
            }
            Iterator<T> it = listCollectKeys.iterator();
            while (it.hasNext()) {
                JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject((String) it.next());
                if (Intrinsics.areEqual(jSONObjectOptJSONObject != null ? jSONObjectOptJSONObject.optString("error") : null, "not_exists")) {
                    Locator locatorFrom = Locator.INSTANCE.from(context);
                    AsserterFactory.createAsserter(((AsserterConfigFactory) locatorFrom.locate(AsserterConfigFactory.class)).createAsserterConfiguration("TornadoSendInlineImagesErrorsAsserter")).fail("Has wrong inline images", new IllegalArgumentException("Not exists inline images"), Descriptions.logs((LogCollector) locatorFrom.locate(LogCollector.class)));
                    return;
                }
            }
        } catch (Exception unused) {
        }
    }

    private final List<String> collectKeys(JSONObject bodyObj) {
        ArrayList arrayList = new ArrayList();
        Iterator<String> itKeys = bodyObj.keys();
        Intrinsics.checkNotNullExpressionValue(itKeys, "keys(...)");
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            if (next != null && StringsKt.startsWith$default(next, "attaches.list", false, 2, (Object) null)) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }
}
