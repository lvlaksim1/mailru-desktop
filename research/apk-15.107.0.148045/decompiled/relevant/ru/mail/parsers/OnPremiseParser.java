package ru.mail.parsers;

import android.net.Uri;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import ru.mail.authorizesdk.data.request.common.SingleRequest;
import ru.mail.cloud.stories.data.gson.parsers.BlockParser;
import ru.mail.credentialsexchanger.data.network.MailOAuthRequest;
import ru.mail.data.cmd.server.AttachesDownloadCmd;
import ru.mail.data.entities.Attach;
import ru.mail.kotlett.spec.DivActionSpec;
import ru.mail.models.Url;
import ru.mail.util.log.Log;
import ru.mail.utils.DynamicHostProviderSharedPreferences;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010$\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010%\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0007J,\u0010\b\u001a\u00020\t2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0003H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lru/mail/parsers/OnPremiseParser;", "", "respString", "", "<init>", "(Ljava/lang/String;)V", "parse", "", "collectOnPremiseHosts", "", BlockParser.MAP_TYPE, "", DivActionSpec.Scroll.PARAM_ITEM_INDEX, "Lru/mail/models/Url;", "link", "Companion", "dns-hosts-parser-impl_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nOnPremiseParser.kt\nKotlin\n*S Kotlin\n*F\n+ 1 OnPremiseParser.kt\nru/mail/parsers/OnPremiseParser\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,84:1\n1869#2,2:85\n*S KotlinDebug\n*F\n+ 1 OnPremiseParser.kt\nru/mail/parsers/OnPremiseParser\n*L\n54#1:85,2\n*E\n"})
public final class OnPremiseParser {

    @NotNull
    private static final Log LOG = Log.INSTANCE.getLog("OnPremiseParser");

    @NotNull
    private final String respString;

    public OnPremiseParser(@NotNull String respString) {
        Intrinsics.checkNotNullParameter(respString, "respString");
        this.respString = respString;
    }

    private final void collectOnPremiseHosts(Map<String, String> map, Url item, String link) {
        String localHost;
        if (link.length() == 0) {
            return;
        }
        Uri uri = Uri.parse(link);
        String localScheme = item.getLocalScheme();
        if (localScheme == null || localScheme.length() == 0 || (localHost = item.getLocalHost()) == null || localHost.length() == 0) {
            if (item.getFullLocalLink() != null) {
                LOG.d("Host was added to map " + item.getFullLocalLink() + " = " + link);
                map.put(item.getFullLocalLink(), link);
                return;
            }
            return;
        }
        String host = uri.getHost();
        if (host == null) {
            host = "";
        }
        String encodedPath = uri.getEncodedPath();
        if (encodedPath == null) {
            encodedPath = "";
        }
        String scheme = uri.getScheme();
        String str = scheme != null ? scheme : "";
        if (StringsKt.contains$default((CharSequence) item.getLocalScheme(), (CharSequence) "push", false, 2, (Object) null)) {
            host = host + encodedPath;
        }
        LOG.d("Host was added to map " + item.getLocalScheme() + " = " + str + StringUtils.SPACE + item.getLocalHost() + " = " + host);
        map.put(item.getLocalScheme(), str);
        map.put(item.getLocalHost(), host);
    }

    @NotNull
    public final Map<String, String> parse() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        List<Url> listListOf = CollectionsKt.listOf((Object[]) new Url[]{new Url("swa", "authentication_v2_base_url"), new Url("account", "account_base_url"), new Url("mail_api", "settings_http_url_host"), new Url("auth", "settings_http_url_host"), new Url("authstat", "settings_http_url_host"), new Url("new_mail_api", "settings_http_url_host"), new Url(MailOAuthRequest.BODY_KEY, "oauth_endpoints_base_url"), new Url("push", "push_base_url"), new Url("avatar", "settings_avatar_host"), new Url("change_avatar", "settings_avatar_host"), new Url(Attach.PREF_ATTACH_PREVIEW, "settings_attachments_preview_url_host"), new Url("domain_settings", "send_domain_settings_host"), new Url(SingleRequest.DefaultServerApi.PREF_KEY, "settings_registration_base_host"), new Url("doreg", "settings_doreg_base_host"), new Url("doreg_captcha", "settings_registration_captcha_host_doreg"), new Url("domain_settings", "settings_domain_settings_base_host"), new Url("rb_default", "rb_default"), new Url(AttachesDownloadCmd.CLOUD_REFERER, AttachesDownloadCmd.CLOUD_REFERER), new Url("cloud_dispatcher", "cloud_dispatcher"), new Url("goauth", "settings_google_oauth2_authentication_host"), new Url("files", "settings_files_host"), new Url("calls", "calls-base-url"), new Url("mail_code_auth_url", null, null, "code_auth"), new Url(null, "avatar_default_scheme_v1", "avatar_default_host_v1", "settings_avatar_host"), new Url(DynamicHostProviderSharedPreferences.OFFLINE_CALENDAR_CONSTANTS, null, null, DynamicHostProviderSharedPreferences.OFFLINE_CALENDAR_CONSTANTS), new Url(DynamicHostProviderSharedPreferences.PORTAL_CALENDAR_URL, null, null, "calendar-touch-page-url"), new Url(DynamicHostProviderSharedPreferences.PORTAL_CALENDAR_NEW_EVENT_URL, null, null, "calendar-create-web-page-url"), new Url(DynamicHostProviderSharedPreferences.ON_PREMISE_APP_VERSION, null, null, DynamicHostProviderSharedPreferences.ON_PREMISE_APP_VERSION), new Url(DynamicHostProviderSharedPreferences.ON_PREMISE_APP_LINK, null, null, DynamicHostProviderSharedPreferences.ON_PREMISE_APP_LINK)});
        if (this.respString.length() > 0) {
            JSONObject jSONObject = new JSONObject(this.respString);
            for (Url url : listListOf) {
                String strOptString = jSONObject.optString(url.getRemoteHostKey(), "");
                Intrinsics.checkNotNullExpressionValue(strOptString, "optString(...)");
                collectOnPremiseHosts(linkedHashMap, url, strOptString);
            }
        }
        return linkedHashMap;
    }
}
