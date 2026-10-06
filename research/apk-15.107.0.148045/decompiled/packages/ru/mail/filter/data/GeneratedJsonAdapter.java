package ru.mail.filter.data;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonDataException;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import com.squareup.moshi.internal.Util;
import java.io.IOException;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.news_feed.util.pulsedeeplinks.ActionParser;

/* JADX INFO: renamed from: ru.mail.filter.data.FilterDtoJsonAdapter, reason: from toString */
/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u0012\u001a\u00020\nH\u0016J\u0010\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u001a\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0002H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lru/mail/filter/data/FilterDtoJsonAdapter;", "Lcom/squareup/moshi/JsonAdapter;", "Lru/mail/filter/data/FilterDto;", "moshi", "Lcom/squareup/moshi/Moshi;", "<init>", "(Lcom/squareup/moshi/Moshi;)V", "options", "Lcom/squareup/moshi/JsonReader$Options;", "stringAdapter", "", "booleanAdapter", "", "listOfConditionDtoAdapter", "", "Lru/mail/filter/data/ConditionDto;", "actionsDtoAdapter", "Lru/mail/filter/data/ActionsDto;", "toString", "fromJson", "reader", "Lcom/squareup/moshi/JsonReader;", "toJson", "", "writer", "Lcom/squareup/moshi/JsonWriter;", "value_", "filter_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class GeneratedJsonAdapter extends JsonAdapter<FilterDto> {

    @NotNull
    private final JsonAdapter<ActionsDto> actionsDtoAdapter;

    @NotNull
    private final JsonAdapter<Boolean> booleanAdapter;

    @NotNull
    private final JsonAdapter<List<ConditionDto>> listOfConditionDtoAdapter;

    @NotNull
    private final JsonReader.Options options;

    @NotNull
    private final JsonAdapter<String> stringAdapter;

    public GeneratedJsonAdapter(@NotNull Moshi moshi) {
        Intrinsics.checkNotNullParameter(moshi, "moshi");
        JsonReader.Options optionsOf = JsonReader.Options.of("id", "enabled", "applyToSpam", "conditions", ActionParser.KEY_MULTIPLE_ACTIONS);
        Intrinsics.checkNotNullExpressionValue(optionsOf, "of(...)");
        this.options = optionsOf;
        JsonAdapter<String> jsonAdapterAdapter = moshi.adapter(String.class, SetsKt.emptySet(), "id");
        Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter, "adapter(...)");
        this.stringAdapter = jsonAdapterAdapter;
        JsonAdapter<Boolean> jsonAdapterAdapter2 = moshi.adapter(Boolean.TYPE, SetsKt.emptySet(), "enabled");
        Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter2, "adapter(...)");
        this.booleanAdapter = jsonAdapterAdapter2;
        JsonAdapter<List<ConditionDto>> jsonAdapterAdapter3 = moshi.adapter(Types.newParameterizedType(List.class, ConditionDto.class), SetsKt.emptySet(), "conditions");
        Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter3, "adapter(...)");
        this.listOfConditionDtoAdapter = jsonAdapterAdapter3;
        JsonAdapter<ActionsDto> jsonAdapterAdapter4 = moshi.adapter(ActionsDto.class, SetsKt.emptySet(), ActionParser.KEY_MULTIPLE_ACTIONS);
        Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter4, "adapter(...)");
        this.actionsDtoAdapter = jsonAdapterAdapter4;
    }

    @NotNull
    public String toString() {
        StringBuilder sb2 = new StringBuilder(31);
        sb2.append("GeneratedJsonAdapter(");
        sb2.append("FilterDto");
        sb2.append(')');
        return sb2.toString();
    }

    @Override // com.squareup.moshi.JsonAdapter
    @NotNull
    public FilterDto fromJson(@NotNull JsonReader reader) throws IOException {
        Intrinsics.checkNotNullParameter(reader, "reader");
        reader.beginObject();
        Boolean boolFromJson = null;
        Boolean boolFromJson2 = null;
        String strFromJson = null;
        List<ConditionDto> listFromJson = null;
        ActionsDto actionsDtoFromJson = null;
        while (reader.hasNext()) {
            int iSelectName = reader.selectName(this.options);
            if (iSelectName == -1) {
                reader.skipName();
                reader.skipValue();
            } else if (iSelectName == 0) {
                strFromJson = this.stringAdapter.fromJson(reader);
                if (strFromJson == null) {
                    JsonDataException jsonDataExceptionUnexpectedNull = Util.unexpectedNull("id", "id", reader);
                    Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull, "unexpectedNull(...)");
                    throw jsonDataExceptionUnexpectedNull;
                }
            } else if (iSelectName == 1) {
                boolFromJson = this.booleanAdapter.fromJson(reader);
                if (boolFromJson == null) {
                    JsonDataException jsonDataExceptionUnexpectedNull2 = Util.unexpectedNull("enabled", "enabled", reader);
                    Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull2, "unexpectedNull(...)");
                    throw jsonDataExceptionUnexpectedNull2;
                }
            } else if (iSelectName == 2) {
                boolFromJson2 = this.booleanAdapter.fromJson(reader);
                if (boolFromJson2 == null) {
                    JsonDataException jsonDataExceptionUnexpectedNull3 = Util.unexpectedNull("applyToSpam", "applyToSpam", reader);
                    Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull3, "unexpectedNull(...)");
                    throw jsonDataExceptionUnexpectedNull3;
                }
            } else if (iSelectName == 3) {
                listFromJson = this.listOfConditionDtoAdapter.fromJson(reader);
                if (listFromJson == null) {
                    JsonDataException jsonDataExceptionUnexpectedNull4 = Util.unexpectedNull("conditions", "conditions", reader);
                    Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull4, "unexpectedNull(...)");
                    throw jsonDataExceptionUnexpectedNull4;
                }
            } else if (iSelectName == 4 && (actionsDtoFromJson = this.actionsDtoAdapter.fromJson(reader)) == null) {
                JsonDataException jsonDataExceptionUnexpectedNull5 = Util.unexpectedNull(ActionParser.KEY_MULTIPLE_ACTIONS, ActionParser.KEY_MULTIPLE_ACTIONS, reader);
                Intrinsics.checkNotNullExpressionValue(jsonDataExceptionUnexpectedNull5, "unexpectedNull(...)");
                throw jsonDataExceptionUnexpectedNull5;
            }
        }
        reader.endObject();
        Boolean bool = boolFromJson2;
        if (strFromJson == null) {
            JsonDataException jsonDataExceptionMissingProperty = Util.missingProperty("id", "id", reader);
            Intrinsics.checkNotNullExpressionValue(jsonDataExceptionMissingProperty, "missingProperty(...)");
            throw jsonDataExceptionMissingProperty;
        }
        if (boolFromJson == null) {
            JsonDataException jsonDataExceptionMissingProperty2 = Util.missingProperty("enabled", "enabled", reader);
            Intrinsics.checkNotNullExpressionValue(jsonDataExceptionMissingProperty2, "missingProperty(...)");
            throw jsonDataExceptionMissingProperty2;
        }
        boolean zBooleanValue = boolFromJson.booleanValue();
        if (bool == null) {
            JsonDataException jsonDataExceptionMissingProperty3 = Util.missingProperty("applyToSpam", "applyToSpam", reader);
            Intrinsics.checkNotNullExpressionValue(jsonDataExceptionMissingProperty3, "missingProperty(...)");
            throw jsonDataExceptionMissingProperty3;
        }
        boolean zBooleanValue2 = bool.booleanValue();
        if (listFromJson == null) {
            JsonDataException jsonDataExceptionMissingProperty4 = Util.missingProperty("conditions", "conditions", reader);
            Intrinsics.checkNotNullExpressionValue(jsonDataExceptionMissingProperty4, "missingProperty(...)");
            throw jsonDataExceptionMissingProperty4;
        }
        if (actionsDtoFromJson != null) {
            return new FilterDto(strFromJson, zBooleanValue, zBooleanValue2, listFromJson, actionsDtoFromJson);
        }
        JsonDataException jsonDataExceptionMissingProperty5 = Util.missingProperty(ActionParser.KEY_MULTIPLE_ACTIONS, ActionParser.KEY_MULTIPLE_ACTIONS, reader);
        Intrinsics.checkNotNullExpressionValue(jsonDataExceptionMissingProperty5, "missingProperty(...)");
        throw jsonDataExceptionMissingProperty5;
    }

    @Override // com.squareup.moshi.JsonAdapter
    public void toJson(@NotNull JsonWriter writer, @Nullable FilterDto value_) throws IOException {
        Intrinsics.checkNotNullParameter(writer, "writer");
        if (value_ == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        writer.beginObject();
        writer.name("id");
        this.stringAdapter.toJson(writer, value_.getId());
        writer.name("enabled");
        this.booleanAdapter.toJson(writer, Boolean.valueOf(value_.getEnabled()));
        writer.name("applyToSpam");
        this.booleanAdapter.toJson(writer, Boolean.valueOf(value_.getApplyToSpam()));
        writer.name("conditions");
        this.listOfConditionDtoAdapter.toJson(writer, value_.getConditions());
        writer.name(ActionParser.KEY_MULTIPLE_ACTIONS);
        this.actionsDtoAdapter.toJson(writer, value_.getActions());
        writer.endObject();
    }
}
