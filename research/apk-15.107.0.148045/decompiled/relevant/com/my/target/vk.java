package com.my.target;

import android.text.TextUtils;
import android.webkit.WebView;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.my.target.common.webform.UserInfo;
import com.vk.superapp.browser.ui.VkUiActivityResultDelegate;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.json.JSONObject;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes2.dex */
public abstract class vk {
    public static void a(yk ykVar, UserInfo userInfo, String str, int i10) {
        WebView webView = ykVar.getWebView();
        if (webView == null) {
            return;
        }
        String strA = a(userInfo, str, i10);
        if (TextUtils.isEmpty(strA)) {
            return;
        }
        webView.evaluateJavascript(strA, null);
    }

    private static String a(UserInfo userInfo, String str, int i10) {
        JSONObject jSONObject;
        JSONObject jSONObject2 = new JSONObject();
        try {
            JSONObject jSONObject3 = new JSONObject();
            UserInfo.Contact contact = userInfo.contact;
            if (contact != null) {
                UserInfo.Contact.DecodingParameters decodingParameters = contact.decodingParameters;
                if (decodingParameters != null) {
                    jSONObject = new JSONObject();
                    jSONObject.put("app_id", decodingParameters.appId);
                    jSONObject.put("user_id", decodingParameters.userId);
                    jSONObject.put("access_token", decodingParameters.accessToken);
                } else {
                    jSONObject = null;
                }
                jSONObject3.put("email", contact.email);
                jSONObject3.put("email_sign", contact.emailSign);
                jSONObject3.put("phone", contact.phone);
                jSONObject3.put("phone_sign", contact.phoneSign);
                jSONObject3.put("decode_params", jSONObject);
            }
            Date date = userInfo.birthday;
            if (date != null) {
                jSONObject3.put("bdate", new SimpleDateFormat("dd.MM.yyyy", Locale.ROOT).format(date));
            }
            jSONObject3.put("country", userInfo.country);
            jSONObject3.put("city", userInfo.city);
            jSONObject3.put(VkUiActivityResultDelegate.KEY_REQUEST_ID, i10);
            jSONObject3.put("first_name", userInfo.firstName);
            jSONObject3.put("last_name", userInfo.lastName);
            int i11 = userInfo.vkId;
            if (i11 > 0) {
                jSONObject3.put("vk_id", i11);
            } else if (!TextUtils.isEmpty(str) && Integer.parseInt(str.replaceAll("id", "")) > 0) {
                jSONObject3.put("vk_id", str);
            }
            JSONObject jSONObject4 = new JSONObject();
            jSONObject4.put("data", jSONObject3);
            jSONObject4.put("type", "VKWebAppGetCustomSdkUserInfoResult");
            jSONObject2.put(ProductAction.ACTION_DETAIL, jSONObject4);
            if (jSONObject2.length() == 0) {
                return null;
            }
            String string = jSONObject2.toString();
            if (TextUtils.isEmpty(string)) {
                return null;
            }
            return "window.dispatchEvent(new CustomEvent('VKWebAppEvent', " + string + "));";
        } catch (Throwable th2) {
            gj.b("WebFormInteractor", th2.getMessage());
            return null;
        }
    }
}
