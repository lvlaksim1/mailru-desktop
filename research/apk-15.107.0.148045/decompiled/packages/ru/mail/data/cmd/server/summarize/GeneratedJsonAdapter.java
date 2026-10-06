package ru.mail.data.cmd.server.summarize;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonDataException;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.internal.Util;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: renamed from: ru.mail.data.cmd.server.summarize.SummarizeJsonAdapter, reason: from toString */
/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u000f\u001a\u00020\nH\u0016J\u0010\u0010\u0010\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u001a\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0002H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lru/mail/data/cmd/server/summarize/SummarizeJsonAdapter;", "Lcom/squareup/moshi/JsonAdapter;", "Lru/mail/data/cmd/server/summarize/Summarize;", "moshi", "Lcom/squareup/moshi/Moshi;", "<init>", "(Lcom/squareup/moshi/Moshi;)V", "options", "Lcom/squareup/moshi/JsonReader$Options;", "stringAdapter", "", "nullableSummarizeRateAdapter", "Lru/mail/data/cmd/server/summarize/SummarizeRate;", "constructorRef", "Ljava/lang/reflect/Constructor;", "toString", "fromJson", "reader", "Lcom/squareup/moshi/JsonReader;", "toJson", "", "writer", "Lcom/squareup/moshi/JsonWriter;", "value_", "mail-app-core_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nSummarizeJsonAdapter.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SummarizeJsonAdapter.kt\nru/mail/data/cmd/server/summarize/SummarizeJsonAdapter\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,95:1\n1#2:96\n*E\n"})
public final class GeneratedJsonAdapter extends JsonAdapter<Summarize> {

    @Nullable
    private volatile Constructor<Summarize> constructorRef;

    @NotNull
    private final JsonAdapter<SummarizeRate> nullableSummarizeRateAdapter;

    @NotNull
    private final JsonReader.Options options;

    @NotNull
    private final JsonAdapter<String> stringAdapter;

    public GeneratedJsonAdapter(@NotNull Moshi moshi) {
        Intrinsics.checkNotNullParameter(moshi, "moshi");
        JsonReader.Options optionsOf = JsonReader.Options.of("text", "rate");
        Intrinsics.checkNotNullExpressionValue(optionsOf, "of(...)");
        this.options = optionsOf;
        JsonAdapter<String> jsonAdapterAdapter = moshi.adapter(String.class, SetsKt.emptySet(), "text");
        Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter, "adapter(...)");
        this.stringAdapter = jsonAdapterAdapter;
        JsonAdapter<SummarizeRate> jsonAdapterAdapter2 = moshi.adapter(SummarizeRate.class, SetsKt.emptySet(), "rate");
        Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter2, "adapter(...)");
        this.nullableSummarizeRateAdapter = jsonAdapterAdapter2;
    }

    @NotNull
    public String toString() {
        StringBuilder sb2 = new StringBuilder(31);
        sb2.append("GeneratedJsonAdapter(");
        sb2.append(SummarizeChunkInterceptor.SUMMARIZE_TAG_PREFIX);
        sb2.append(')');
        return sb2.toString();
    }

    @Override // com.squareup.moshi.JsonAdapter
    @NotNull
    public Summarize fromJson(@NotNull JsonReader reader) throws IllegalAccessException, NoSuchMethodException, InstantiationException, IOException, InvocationTargetException {
        Intrinsics.checkNotNullParameter(reader, "reader");
        reader.beginObject();
        String strFromJson = null;
        SummarizeRate summarizeRateFromJson = null;
        int i10 = -1;
        while (reader.hasNext()) {
            int iSelectName = reader.selectName(this.options);
            if (iSelectName == -1) {
                reader.skipName();
                reader.skipValue();
            } else if (iSelectName == 0) {
                strFromJson = this.stringAdapter.fromJson(reader);
                if (strFromJson == null) {
                    JsonDataException jsonDataExceptionUnexpectedNull = Util.unexpectedNull("text", "text", reader);
                    Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull, "unexpectedNull(...)");
                    throw jsonDataExceptionUnexpectedNull;
                }
            } else if (iSelectName == 1) {
                summarizeRateFromJson = this.nullableSummarizeRateAdapter.fromJson(reader);
                i10 = -3;
            }
        }
        reader.endObject();
        if (i10 == -3) {
            if (strFromJson != null) {
                return new Summarize(strFromJson, summarizeRateFromJson);
            }
            JsonDataException jsonDataExceptionMissingProperty = Util.missingProperty("text", "text", reader);
            Intrinsics.checkNotNullExpressionValue(jsonDataExceptionMissingProperty, "missingProperty(...)");
            throw jsonDataExceptionMissingProperty;
        }
        Constructor<Summarize> declaredConstructor = this.constructorRef;
        if (declaredConstructor == null) {
            declaredConstructor = Summarize.class.getDeclaredConstructor(String.class, SummarizeRate.class, Integer.TYPE, Util.f39805c);
            this.constructorRef = declaredConstructor;
            Intrinsics.checkNotNullExpressionValue(declaredConstructor, "also(...)");
        }
        if (strFromJson != null) {
            Summarize summarizeNewInstance = declaredConstructor.newInstance(strFromJson, summarizeRateFromJson, Integer.valueOf(i10), null);
            Intrinsics.checkNotNullExpressionValue(summarizeNewInstance, "newInstance(...)");
            return summarizeNewInstance;
        }
        JsonDataException jsonDataExceptionMissingProperty2 = Util.missingProperty("text", "text", reader);
        Intrinsics.checkNotNullExpressionValue(jsonDataExceptionMissingProperty2, "missingProperty(...)");
        throw jsonDataExceptionMissingProperty2;
    }

    @Override // com.squareup.moshi.JsonAdapter
    public void toJson(@NotNull JsonWriter writer, @Nullable Summarize value_) throws IOException {
        Intrinsics.checkNotNullParameter(writer, "writer");
        if (value_ == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        writer.beginObject();
        writer.name("text");
        this.stringAdapter.toJson(writer, value_.getText());
        writer.name("rate");
        this.nullableSummarizeRateAdapter.toJson(writer, value_.getRate());
        writer.endObject();
    }
}
