package ru.mail.auth.webview;

import android.text.TextUtils;
import ru.mail.auth.request.GetEmailRequest;
import ru.mail.auth.request.YandexEmailRequest;
import ru.mail.deviceinfo.DeviceIdProvider;
import ru.mail.deviceinfo.DeviceInfo;
import ru.mail.deviceinfo.di.DeviceInfoEntryPoint;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
public class YandexOauth2AccessTokenFragment extends OAuthAccessTokenFragment {
    @Override // ru.mail.auth.webview.OAuthAccessTokenFragment
    public OAuthAccessTokenFragment.EmailHolder getEmailHolder() {
        return new OAuthAccessTokenFragment.EmailHolderWithRequest() { // from class: ru.mail.auth.webview.YandexOauth2AccessTokenFragment.1
            @Override // ru.mail.auth.webview.OAuthAccessTokenFragment.EmailHolderWithRequest
            public GetEmailRequest<?> getEmailRequestCmd(String str) {
                return new YandexEmailRequest(YandexOauth2AccessTokenFragment.this.getLpmitnenopmochtuaokvmocb(), str);
            }
        };
    }

    @Override // ru.mail.auth.webview.OAuthAccessTokenFragment
    protected String getSpecificAuthUrlParams() {
        StringBuilder sb2 = new StringBuilder();
        if (!TextUtils.isEmpty(getLoginHintFromIntent())) {
            sb2.append("&login_hint=");
            sb2.append(getLoginHintFromIntent());
        }
        DeviceIdProvider deviceIdProvider = DeviceInfoEntryPoint.deviceIdProvider(getLpmitnenopmochtuaokvmocb());
        sb2.append("&device_id=");
        sb2.append(deviceIdProvider.getDeviceId());
        DeviceInfo deviceInfo = DeviceInfoEntryPoint.deviceInfoFactory(getLpmitnenopmochtuaokvmocb()).getDeviceInfo();
        sb2.append("&device_name=");
        sb2.append(String.format("%s Android %s", deviceInfo.getModel(), deviceInfo.getOsVersion()));
        sb2.append("&force_confirm=yes");
        return sb2.toString();
    }
}
