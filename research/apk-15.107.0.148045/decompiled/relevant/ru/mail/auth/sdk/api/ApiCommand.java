package ru.mail.auth.sdk.api;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import androidx.annotation.NonNull;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import kotlin.text.Typography;
import ru.mail.auth.sdk.MailRuAuthSdk;
import ru.mail.auth.sdk.call.CallException;
import ru.mail.auth.sdk.call.MethodCall;
import ru.mail.util.log.Formats;
import ru.mail.util.log.LogFilter;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
public abstract class ApiCommand<R> implements MethodCall<R> {
    private static final String TAG = "ApiCommand";
    private static final Charset sDefaultCharset = Charset.forName("UTF-8");
    private static final LogFilter sLogFilter = new LogFilter(Formats.newUrlFormat("token"), Formats.newUrlFormat("access_token"));

    private String buildUrl(ApiQuery apiQuery) {
        Uri.Builder builderBuildUpon = Uri.parse(apiQuery.getHost()).buildUpon();
        builderBuildUpon.path(apiQuery.getMethodName());
        if (!apiQuery.getGetArgs().isEmpty()) {
            for (Pair<String, String> pair : apiQuery.getGetArgs()) {
                builderBuildUpon.appendQueryParameter((String) pair.first, (String) pair.second);
            }
        }
        return builderBuildUpon.toString();
    }

    private byte[] getPostData(ApiQuery apiQuery) {
        String string;
        try {
            if (TextUtils.isEmpty(apiQuery.getPostBody())) {
                StringBuilder sb2 = new StringBuilder();
                for (Pair<String, String> pair : apiQuery.getPostArgs()) {
                    if (sb2.length() != 0) {
                        sb2.append(Typography.amp);
                    }
                    String str = (String) pair.first;
                    Charset charset = sDefaultCharset;
                    sb2.append(URLEncoder.encode(str, charset.name()));
                    sb2.append('=');
                    sb2.append(URLEncoder.encode((String) pair.second, charset.name()));
                }
                string = sb2.toString();
            } else {
                string = apiQuery.getPostBody();
            }
            return string.getBytes(sDefaultCharset.name());
        } catch (UnsupportedEncodingException unused) {
            return new byte[0];
        }
    }

    private Pair<Integer, InputStream> performRequest(ApiQuery apiQuery) throws IOException {
        URL url = new URL(buildUrl(apiQuery));
        Log.d(TAG, "Requesting url " + sLogFilter.filter(url.toString()));
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setConnectTimeout(20000);
        httpURLConnection.setReadTimeout(20000);
        httpURLConnection.setRequestMethod(apiQuery.getMethod().name());
        if (apiQuery.getMethod() == ApiQuery.Method.POST) {
            byte[] postData = getPostData(apiQuery);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestProperty("Content-Type", apiQuery.getContentType().getRepr());
            httpURLConnection.setRequestProperty("Content-Length", String.valueOf(postData.length));
            httpURLConnection.getOutputStream().write(postData);
        }
        httpURLConnection.connect();
        int responseCode = httpURLConnection.getResponseCode();
        Log.d(TAG, "Response code " + responseCode);
        return responseCode == 200 ? new Pair<>(Integer.valueOf(responseCode), httpURLConnection.getInputStream()) : new Pair<>(Integer.valueOf(responseCode), new ByteArrayInputStream(new byte[0]));
    }

    private String readResponse(InputStream inputStream) throws IOException {
        byte[] bArr = new byte[8192];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(8192);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream, 8192);
        while (true) {
            int i10 = bufferedInputStream.read(bArr);
            if (i10 <= 0) {
                return new String(byteArrayOutputStream.toByteArray(), sDefaultCharset);
            }
            byteArrayOutputStream.write(bArr, 0, i10);
        }
    }

    @Override // ru.mail.auth.sdk.call.MethodCall
    @NonNull
    public final R execute() throws CallException {
        ApiQuery query = getQuery();
        ResponseProcessor<R> responseProcessor = getResponseProcessor();
        Pair<Integer, InputStream> pairPerformRequest = null;
        try {
            try {
                try {
                    pairPerformRequest = performRequest(query);
                    String response = readResponse((InputStream) pairPerformRequest.second);
                    if (MailRuAuthSdk.getInstance().isDebugEnabled()) {
                        Log.d(TAG, "Response " + response);
                    }
                    R rProcess = responseProcessor.process(((Integer) pairPerformRequest.first).intValue(), response);
                    try {
                        ((InputStream) pairPerformRequest.second).close();
                        return rProcess;
                    } catch (IOException e10) {
                        Log.e(TAG, "Stream close", e10);
                        return rProcess;
                    }
                } catch (Throwable th2) {
                    if (pairPerformRequest != null) {
                        try {
                            ((InputStream) pairPerformRequest.second).close();
                        } catch (IOException e11) {
                            Log.e(TAG, "Stream close", e11);
                        }
                    }
                    throw th2;
                }
            } catch (MalformedURLException e12) {
                Log.e(TAG, "Bad url", e12);
                throw new CallException(-3, "Bad url");
            }
        } catch (IOException e13) {
            Log.e(TAG, "Connect exception", e13);
            throw new CallException(-1, e13.getMessage());
        }
    }

    protected abstract ApiQuery getQuery();

    protected abstract ResponseProcessor<R> getResponseProcessor();
}
