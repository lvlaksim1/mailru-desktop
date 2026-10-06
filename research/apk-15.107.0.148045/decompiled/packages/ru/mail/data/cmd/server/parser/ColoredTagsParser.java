package ru.mail.data.cmd.server.parser;

import com.google.android.gms.ads.RequestConfiguration;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.data.entities.Collector;
import ru.mail.data.entities.ColoredLabels;
import ru.mail.data.entities.MailMessage;
import ru.mail.data.entities.MailThreadRepresentation;
import ru.mail.data.entities.MessageLabel;
import ru.mail.data.entities.ThreadLabel;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001c\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u001c\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\r0\u00072\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000fJ?\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\u00110\u0007\"\b\b\u0000\u0010\u0011*\u00020\u0012*\u00020\n2!\u0010\u0013\u001a\u001d\u0012\u0013\u0012\u00110\u0015¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0018\u0012\u0004\u0012\u0002H\u00110\u0014H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lru/mail/data/cmd/server/parser/ColoredTagsParser;", "", "isColoredTagsOn", "", "<init>", "(Z)V", "parse", "", "Lru/mail/data/entities/ThreadLabel;", Collector.FLAGS, "Lorg/json/JSONObject;", "representation", "Lru/mail/data/entities/MailThreadRepresentation;", "Lru/mail/data/entities/MessageLabel;", "mail", "Lru/mail/data/entities/MailMessage;", "collectTags", RequestConfiguration.MAX_AD_CONTENT_RATING_T, "Lru/mail/data/entities/ColoredLabels;", "action", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "tag", "Companion", "message_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nColoredTagsParser.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ColoredTagsParser.kt\nru/mail/data/cmd/server/parser/ColoredTagsParser\n+ 2 UtilExtensions.kt\nru/mail/utils/UtilExtensionsKt\n*L\n1#1,47:1\n88#2,4:48\n*S KotlinDebug\n*F\n+ 1 ColoredTagsParser.kt\nru/mail/data/cmd/server/parser/ColoredTagsParser\n*L\n36#1:48,4\n*E\n"})
public final class ColoredTagsParser {

    @NotNull
    private static final String TAGS = "tags";
    private final boolean isColoredTagsOn;

    public ColoredTagsParser(boolean z10) {
        this.isColoredTagsOn = z10;
    }

    private final <T extends ColoredLabels> List<T> collectTags(JSONObject jSONObject, Function1<? super String, ? extends T> function1) throws JSONException {
        ArrayList arrayList = new ArrayList();
        if (this.isColoredTagsOn && jSONObject.has("tags")) {
            JSONArray jSONArray = jSONObject.getJSONArray("tags");
            Intrinsics.checkNotNull(jSONArray);
            int length = jSONArray.length();
            for (int i10 = 0; i10 < length; i10++) {
                String string = jSONArray.getString(i10);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                arrayList.add(function1.invoke(string));
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ThreadLabel parse$lambda$0(MailThreadRepresentation mailThreadRepresentation, String tag) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        return new ThreadLabel(tag, mailThreadRepresentation, mailThreadRepresentation.getMailThreadId());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MessageLabel parse$lambda$1(MailMessage mailMessage, String tag) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        String mailMessageId = mailMessage.getMailMessageId();
        Intrinsics.checkNotNullExpressionValue(mailMessageId, "getMailMessageId(...)");
        return new MessageLabel(tag, mailMessage, mailMessageId);
    }

    @NotNull
    public final List<ThreadLabel> parse(@NotNull JSONObject flags, @NotNull final MailThreadRepresentation representation) throws JSONException {
        Intrinsics.checkNotNullParameter(flags, "flags");
        Intrinsics.checkNotNullParameter(representation, "representation");
        return collectTags(flags, new Function1() { // from class: ru.mail.data.cmd.server.parser.a
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ColoredTagsParser.parse$lambda$0(representation, (String) obj);
            }
        });
    }

    @NotNull
    public final List<MessageLabel> parse(@NotNull JSONObject flags, @NotNull final MailMessage mail) throws JSONException {
        Intrinsics.checkNotNullParameter(flags, "flags");
        Intrinsics.checkNotNullParameter(mail, "mail");
        return collectTags(flags, new Function1() { // from class: ru.mail.data.cmd.server.parser.b
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ColoredTagsParser.parse$lambda$1(mail, (String) obj);
            }
        });
    }
}
