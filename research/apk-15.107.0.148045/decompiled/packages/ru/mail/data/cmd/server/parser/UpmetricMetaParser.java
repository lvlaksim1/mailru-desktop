package ru.mail.data.cmd.server.parser;

import androidx.credentials.playservices.controllers.CredentialProviderBaseController;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.LongCompanionObject;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.logic.content.UpmetricBanner;
import ru.mail.util.log.Log;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010$\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0014\u0010\u0017\u001a\u0004\u0018\u00010\u00022\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016J\u0012\u0010\u001a\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u0010\u0010\u001b\u001a\u00020\b2\u0006\u0010\u001c\u001a\u00020\bH\u0002J\u0016\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001c\u001a\u00020\b2\u0006\u0010\u001f\u001a\u00020 J\u001c\u0010!\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\"2\u0006\u0010#\u001a\u00020\u0019H\u0002J\u001c\u0010$\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\"2\u0006\u0010%\u001a\u00020\u0019H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lru/mail/data/cmd/server/parser/UpmetricMetaParser;", "Lru/mail/data/cmd/server/parser/JSONParser;", "Lru/mail/logic/content/UpmetricBanner;", "<init>", "()V", "LOG", "Lru/mail/util/log/Log;", "CONTEXT", "", CredentialProviderBaseController.TYPE_TAG, "ID", "IMAGES_MOBILE", "IMAGES_WEB", "ICON_URL", "DESCRIPTION", "CLICK_URLS", "PIXEL_URL", "PROMOCODE", "PROMOCODE_EXPIRATION_DATE", "ADS_LABEL", "EXPIRATION_DATE", "DATE_PATTERN", "PROMOCODE_EXPIRATION_DATE_PATTERN", "parse", "jsonObject", "Lorg/json/JSONObject;", "parseInternal", "parsePromocodeExpirationDate", "timeString", "parseTime", "Ljava/util/Date;", "defaultValue", "", "parseImagesWeb", "", "imagesWebJSON", "parseImagesMobile", "imagesMobileJSON", "message_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nUpmetricMetaParser.kt\nKotlin\n*S Kotlin\n*F\n+ 1 UpmetricMetaParser.kt\nru/mail/data/cmd/server/parser/UpmetricMetaParser\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,180:1\n1740#2,3:181\n*S KotlinDebug\n*F\n+ 1 UpmetricMetaParser.kt\nru/mail/data/cmd/server/parser/UpmetricMetaParser\n*L\n60#1:181,3\n*E\n"})
public final class UpmetricMetaParser extends JSONParser<UpmetricBanner> {

    @NotNull
    private static final String ADS_LABEL = "ordInfo";

    @NotNull
    private static final String CLICK_URLS = "linkMobile";

    @NotNull
    private static final String CONTEXT = "@context";

    @NotNull
    private static final String DATE_PATTERN = "yyyy-MM-dd";

    @NotNull
    private static final String DESCRIPTION = "promoText";

    @NotNull
    private static final String EXPIRATION_DATE = "activeBefore";

    @NotNull
    private static final String ICON_URL = "promoIcon";

    @NotNull
    private static final String ID = "creativeId";

    @NotNull
    private static final String IMAGES_MOBILE = "bannerMobile";

    @NotNull
    private static final String IMAGES_WEB = "bannerWeb";

    @NotNull
    public static final UpmetricMetaParser INSTANCE = new UpmetricMetaParser();

    @NotNull
    private static final Log LOG = Log.INSTANCE.getLog("UpmetricMetaParser");

    @NotNull
    private static final String PIXEL_URL = "actionPixelEmail";

    @NotNull
    private static final String PROMOCODE = "personalPromocode";

    @NotNull
    private static final String PROMOCODE_EXPIRATION_DATE = "personalPromocodeActiveBefore";

    @NotNull
    private static final String PROMOCODE_EXPIRATION_DATE_PATTERN = "dd.MM.yyyy";

    @NotNull
    private static final String TYPE = "@type";

    private UpmetricMetaParser() {
    }

    private final Map<String, String> parseImagesMobile(JSONObject imagesMobileJSON) {
        String strOptString = imagesMobileJSON.optString(UpmetricBanner.SIZE_MOBILE_1);
        Intrinsics.checkNotNull(strOptString);
        if (strOptString.length() == 0) {
            strOptString = imagesMobileJSON.optString(UpmetricBanner.SIZE_MOBILE_1_R);
        }
        String strOptString2 = imagesMobileJSON.optString(UpmetricBanner.SIZE_MOBILE_2);
        Intrinsics.checkNotNull(strOptString2);
        if (strOptString2.length() == 0) {
            strOptString2 = imagesMobileJSON.optString(UpmetricBanner.SIZE_MOBILE_2_R);
        }
        String strOptString3 = imagesMobileJSON.optString(UpmetricBanner.SIZE_MOBILE_3);
        Intrinsics.checkNotNull(strOptString3);
        if (strOptString3.length() == 0) {
            strOptString3 = imagesMobileJSON.optString(UpmetricBanner.SIZE_MOBILE_3_R);
        }
        return MapsKt.mapOf(TuplesKt.to(UpmetricBanner.SIZE_MOBILE_1, strOptString), TuplesKt.to(UpmetricBanner.SIZE_MOBILE_2, strOptString2), TuplesKt.to(UpmetricBanner.SIZE_MOBILE_3, strOptString3));
    }

    private final Map<String, String> parseImagesWeb(JSONObject imagesWebJSON) {
        String strOptString = imagesWebJSON.optString(UpmetricBanner.SIZE_WEB_1);
        Intrinsics.checkNotNull(strOptString);
        if (strOptString.length() == 0) {
            strOptString = imagesWebJSON.optString(UpmetricBanner.SIZE_WEB_1_R);
        }
        String strOptString2 = imagesWebJSON.optString(UpmetricBanner.SIZE_WEB_2);
        Intrinsics.checkNotNull(strOptString2);
        if (strOptString2.length() == 0) {
            strOptString2 = imagesWebJSON.optString(UpmetricBanner.SIZE_WEB_2_R);
        }
        String strOptString3 = imagesWebJSON.optString(UpmetricBanner.SIZE_WEB_3);
        Intrinsics.checkNotNull(strOptString3);
        if (strOptString3.length() == 0) {
            strOptString3 = imagesWebJSON.optString(UpmetricBanner.SIZE_WEB_3_R);
        }
        return MapsKt.mapOf(TuplesKt.to(UpmetricBanner.SIZE_WEB_1, strOptString), TuplesKt.to(UpmetricBanner.SIZE_WEB_2, strOptString2), TuplesKt.to(UpmetricBanner.SIZE_WEB_3, strOptString3));
    }

    private final UpmetricBanner parseInternal(JSONObject jsonObject) throws JSONException {
        String string = jsonObject.getString(CONTEXT);
        if (!Intrinsics.areEqual(string, UpmetricBanner.CONTEXT)) {
            throw new JSONException("Unknown meta context: " + string);
        }
        String string2 = jsonObject.getString(TYPE);
        if (!Intrinsics.areEqual(string2, UpmetricBanner.TYPE)) {
            throw new JSONException("Unknown meta type: " + string2);
        }
        int i10 = jsonObject.getInt(ID);
        JSONObject jSONObjectOptJSONObject = jsonObject.optJSONObject(IMAGES_MOBILE);
        JSONObject jSONObjectOptJSONObject2 = jsonObject.optJSONObject(IMAGES_WEB);
        if (jSONObjectOptJSONObject != null && jSONObjectOptJSONObject2 != null) {
            Map<String, String> imagesWeb = parseImagesWeb(jSONObjectOptJSONObject2);
            Map<String, String> imagesMobile = parseImagesMobile(jSONObjectOptJSONObject);
            Collection<String> collectionValues = imagesWeb.values();
            if (!(collectionValues instanceof Collection) || !collectionValues.isEmpty()) {
                Iterator<T> it = collectionValues.iterator();
                while (it.hasNext()) {
                    if (((String) it.next()).length() != 0) {
                        Collection<String> collectionValues2 = imagesMobile.values();
                        if (!(collectionValues2 instanceof Collection) || !collectionValues2.isEmpty()) {
                            Iterator<T> it2 = collectionValues2.iterator();
                            while (it2.hasNext()) {
                                if (((String) it2.next()).length() != 0) {
                                    String strOptString = jsonObject.optString(PIXEL_URL);
                                    String strOptString2 = jsonObject.optString(CLICK_URLS);
                                    if (strOptString2 == null || StringsKt.isBlank(strOptString2)) {
                                        break;
                                        break;
                                    }
                                    String strOptString3 = jsonObject.optString(ADS_LABEL);
                                    String strOptString4 = jsonObject.optString(EXPIRATION_DATE);
                                    Intrinsics.checkNotNull(strOptString4);
                                    Date time = strOptString4.length() > 0 ? parseTime(strOptString4, LongCompanionObject.MAX_VALUE) : new Date(LongCompanionObject.MAX_VALUE);
                                    String strOptString5 = jsonObject.optString(ICON_URL);
                                    String strOptString6 = jsonObject.optString(DESCRIPTION);
                                    String strOptString7 = jsonObject.optString(PROMOCODE);
                                    String strOptString8 = jsonObject.optString(PROMOCODE_EXPIRATION_DATE);
                                    Intrinsics.checkNotNull(strOptString8);
                                    String promocodeExpirationDate = strOptString8.length() > 0 ? parsePromocodeExpirationDate(strOptString8) : "";
                                    Intrinsics.checkNotNull(strOptString);
                                    Intrinsics.checkNotNull(strOptString3);
                                    Intrinsics.checkNotNull(strOptString5);
                                    Intrinsics.checkNotNull(strOptString6);
                                    Intrinsics.checkNotNull(strOptString7);
                                    return new UpmetricBanner(i10, imagesMobile, imagesWeb, strOptString, strOptString2, strOptString3, time, strOptString5, strOptString6, strOptString7, promocodeExpirationDate);
                                }
                            }
                            break;
                        }
                        break;
                    }
                }
            }
        }
        return null;
    }

    private final String parsePromocodeExpirationDate(String timeString) {
        try {
            Locale locale = Locale.ENGLISH;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", locale);
            SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(PROMOCODE_EXPIRATION_DATE_PATTERN, locale);
            Date date = simpleDateFormat.parse(timeString);
            if (date == null) {
                return "";
            }
            String str = simpleDateFormat2.format(date);
            Intrinsics.checkNotNull(str);
            return str;
        } catch (ParseException e10) {
            LOG.e("Parsing date failed: " + timeString, e10);
            return "";
        }
    }

    @NotNull
    public final Date parseTime(@NotNull String timeString, long defaultValue) throws ParseException {
        Intrinsics.checkNotNullParameter(timeString, "timeString");
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(timeString);
            return date == null ? new Date(defaultValue) : date;
        } catch (ParseException e10) {
            LOG.e("Parsing date failed: " + timeString, e10);
            return new Date(defaultValue);
        }
    }

    @Override // ru.mail.data.cmd.server.parser.JSONParser
    @Nullable
    public UpmetricBanner parse(@Nullable JSONObject jsonObject) throws JSONException {
        if (jsonObject == null) {
            return null;
        }
        try {
            return parseInternal(jsonObject);
        } catch (JSONException e10) {
            LOG.e("Parsing UpmetricMeta failed, json: " + jsonObject, e10);
            throw new JSONException("Parsing UpmetricMeta failed: " + e10.getStackTrace());
        }
    }
}
